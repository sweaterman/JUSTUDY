package com.justudy.backend.community.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.dto.request.CategoryResponse;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.response.CommunityDetailResponse;
import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.community.dto.response.CommunityListResult;
import com.justudy.backend.community.service.CommunityBookmarkService;
import com.justudy.backend.community.service.CommunityCommentService;
import com.justudy.backend.community.service.CommunityLoveService;
import com.justudy.backend.community.service.CommunityService;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CommunityController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class CommunityControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    MemberService memberService;
    @MockBean
    CommunityService communityService;
    @MockBean
    CommunityCommentService communityCommentService;
    @MockBean
    CommunityLoveService communityLoveService;
    @MockBean
    CommunityBookmarkService communityBookmarkService;
    @MockBean
    CategoryService categoryService;

    private final String COMMON_URL = "/api/community";

    private final String CATEGORY_KEY = "backend";
    private final String CATEGORY_VALUE = "BACK-END";
    private final String TITLE = "테스트제목";
    private final String CONTENT = "테스트내용";

    @Test
    @DisplayName("게시글 리스트 조회 - 일반, 좋아요 순")
    void getList() throws Exception {
        //given
        CommunitySearch nullCondition = createCondition(null, null, null);
        System.out.println(nullCondition);
        CommunitySearch likeCondition = createCondition(null, null, "like");
        System.out.println(likeCondition);

        List<CommunityListResponse> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            list.add(new CommunityListResponse((long) i, "타이틀" + i, "내용" + i, LocalDateTime.now(), false, i, 1000 - i));
        }
        List<CommunityListResponse> sortedSequence = list.stream().sorted(Comparator.comparing(CommunityListResponse::getSequence).reversed())
                .collect(Collectors.toList());
        List<CommunityListResponse> sortedLoveCount = list.stream().sorted(Comparator.comparing(CommunityListResponse::getLoveCount).reversed())
                .collect(Collectors.toList());
        BDDMockito.given(communityService.getCommunities(ArgumentMatchers.eq(nullCondition)))
                .willReturn(new CommunityListResult<>(sortedSequence, 30L));
        BDDMockito.given(communityService.getCommunities(ArgumentMatchers.eq(likeCondition)))
                .willReturn(new CommunityListResult<>(sortedLoveCount, 30L));

        //expected
        mockMvc.perform(get(COMMON_URL + "/board")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.communityList.size()", is(30)))
                .andExpect(jsonPath("$.communityList[0].sequence", is(30)))
                .andDo(print());
        mockMvc.perform(get(COMMON_URL + "/board?&order=like")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.communityList.size()", is(30)))
                .andExpect(jsonPath("$.communityList[0].sequence").value(1))
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 상세 조회 [GET] /board/{id}")
    void getCommunityDetail() throws Exception {
        //given
        final Long LOGIN_SEQUENCE = 1000L;
        final Long COMMUNITY_SEQUENCE = 15L;
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_USER, LOGIN_SEQUENCE);

        CommunityDetailResponse response = CommunityDetailResponse.builder()
                .sequence(COMMUNITY_SEQUENCE)
                .title("제목")
                .content("내용")
                .category(new CategoryResponse("frontend", "FRONT-END"))
                .build();

        BDDMockito.given(communityService.readCommunityDetail(COMMUNITY_SEQUENCE, LOGIN_SEQUENCE))
                .willReturn(response);

        mockMvc.perform(get(COMMON_URL + "/board/{id}", COMMUNITY_SEQUENCE)
                        .session(session))
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andExpect(jsonPath("$.category.key").value("frontend"))
                .andExpect(jsonPath("$.category.value").value("FRONT-END"))
                .andDo(print());

        BDDMockito.then(communityService).should(times(1)).readCommunityDetail(COMMUNITY_SEQUENCE, LOGIN_SEQUENCE);
    }

    @Test
    @DisplayName("게시글 생성 [POST] /board")
    void createCommunity() throws Exception {
        //given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_USER, 5L);

        MemberEntity mockMember = makeTestMember("test", "test", "test");
        ReflectionTestUtils.setField(mockMember, "sequence", 5L);
        BDDMockito.given(memberService.getMember(anyLong()))
                .willReturn(mockMember);

        CategoryEntity mockCategory = new CategoryEntity(CATEGORY_KEY, CATEGORY_VALUE, 0L, null);
        ReflectionTestUtils.setField(mockCategory, "sequence", 7L);
        BDDMockito.given(categoryService.getCategoryEntityByKey("backend"))
                .willReturn(mockCategory);

        CommunityCreate request = CommunityCreate.builder()
                .category(CATEGORY_KEY)
                .title(TITLE)
                .content(CONTENT)
                .build();
        BDDMockito.given(communityService.createCommunity(request, mockMember, mockCategory))
                .willReturn(makeCommunityResponse(mockMember, mockCategory));

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post(COMMON_URL + "/board")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sequence").value(10L))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.content").value(CONTENT))
                .andExpect(jsonPath("$.category.key").value(CATEGORY_KEY))
                .andExpect(jsonPath("$.category.value").value(CATEGORY_VALUE))
                .andDo(print());

        BDDMockito.then(communityService).should().createCommunity(request, mockMember, mockCategory);
    }

    @Test
    @DisplayName("게시글 삭제 [DELETE] /board/{id}")
    void deleteCommunity() throws Exception {
        //given
        final Long COMMUNITY_SEQUENCE = 100L;
        final Long LOGIN_SEQUENCE = 1L;
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_USER, LOGIN_SEQUENCE);

        BDDMockito.given(communityService.deleteCommunity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE))
                .willReturn(COMMUNITY_SEQUENCE);

        //expected
        mockMvc.perform(delete(COMMON_URL + "/board/{sequence}", COMMUNITY_SEQUENCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session))
                .andExpect(status().isNoContent());
        BDDMockito.then(communityService)
                .should(times(1))
                .deleteCommunity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);
    }

    @Test
    @DisplayName("게시글 업데이트 [PUT] /board/{id}")
    void updateCommunity() throws Exception {
        //given
        final Long LOGIN_SEQUENCE = 15L;
        final Long COMMUNITY_SEQUENCE = 100L;

        final String NEW_TITLE = "수정제목";
        final String NEW_CONTENT = "수정내용";
        final String NEW_CATEGORY = "algorithm";

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(SessionConst.LOGIN_USER, LOGIN_SEQUENCE);

        CommunityEdit request = new CommunityEdit(NEW_TITLE, NEW_CONTENT, NEW_CATEGORY);
        String json = objectMapper.writeValueAsString(request);

        CategoryResponse categoryResponse = new CategoryResponse(NEW_CATEGORY, "Algorithm");
        CommunityDetailResponse response = makeEditResponse(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE, NEW_TITLE, NEW_CONTENT, categoryResponse);
        BDDMockito.given(communityService.updateCommunity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE, request))
                .willReturn(response);

        //expected
        mockMvc.perform(put(COMMON_URL + "/board/{id}", COMMUNITY_SEQUENCE)
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(jsonPath("$.title").value(NEW_TITLE))
                .andExpect(jsonPath("$.content").value(NEW_CONTENT))
                .andExpect(jsonPath("$.category.key").value(NEW_CATEGORY))
                .andExpect(jsonPath("$.category.value").value("Algorithm"))
                .andDo(print());
    }

    private static CommunitySearch createCondition(String type, String search, String order) {
        return CommunitySearch.builder()
                .page(0L)
                .size(20L)
                .type(type)
                .search(search)
                .order(order)
                .build();
    }

    private CommunityDetailResponse makeEditResponse(Long loginSequence,
                                                     Long communitySequence,
                                                     String title,
                                                     String content,
                                                     CategoryResponse categoryResponse) {
        return CommunityDetailResponse.builder()
                .sequence(communitySequence)
                .memberSequence(loginSequence)
                .nickname("nickname")
                .category(categoryResponse)
                .title(title)
                .content(content)
                .viewCount(15)
                .build();
    }

    private CommunityDetailResponse makeCommunityResponse(MemberEntity mockMember, CategoryEntity mockCategory) {
        return CommunityDetailResponse.builder()
                .sequence(10L)
                .memberSequence(mockMember.getSequence())
                .nickname(mockMember.getNickname())
                .category(new CategoryResponse(mockCategory))
                .title(TITLE)
                .content(CONTENT)
                .viewCount(0)
                .createdTime(LocalDateTime.now())
                .modifiedTime(LocalDateTime.now())
                .loveCount(0)
                .build();
    }

    private MemberEntity makeTestMember(String userId, String nickname, String ssafyId) {
        return MemberEntity.builder()
                .userId(userId)
                .password("1234")
                .username("이신광")
                .nickname(nickname)
                .ssafyId(ssafyId)
                .phone("01011111111")
                .email("ssafylee@ssafy.com")
                .region(Region.SEOUL)
                .dream("백엔드 취업희망")
                .introduction("안녕하세요")
                .build();
    }
}