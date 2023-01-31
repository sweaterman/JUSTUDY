package com.justudy.backend.community.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.response.CommunityResponse;
import com.justudy.backend.community.service.CommunityBookmarkService;
import com.justudy.backend.community.service.CommunityCommentService;
import com.justudy.backend.community.service.CommunityLoveService;
import com.justudy.backend.community.service.CommunityService;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommunityController.class)
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

    private final String CATEGORY_BACKEND = "backend";
    private final String TITLE = "테스트제목";
    private final String CONTENT = "테스트내용";

    @Test
    @DisplayName("게시글 조회 [GET] /board/{id}")
    void getCommunity() throws Exception {
        //given
        final Long COMMUNITY_SEQUENCE = 15L;
        CommunityResponse response = CommunityResponse.builder()
                .sequence(COMMUNITY_SEQUENCE)
                .title("제목")
                .content("내용")
                .category("frontend")
                .build();

        BDDMockito.given(communityService.readCommunity(15L))
                .willReturn(response);

        mockMvc.perform(get(COMMON_URL + "/board/{id}", COMMUNITY_SEQUENCE))
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andExpect(jsonPath("$.category").value("frontend"))
                .andDo(print());

        BDDMockito.then(communityService).should(Mockito.only()).readCommunity(15L);
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

        CategoryEntity mockCategory = new CategoryEntity("backend", 0L);
        ReflectionTestUtils.setField(mockCategory, "sequence", 7L);
        BDDMockito.given(categoryService.getCategory("backend"))
                .willReturn(mockCategory);

        CommunityCreate request = CommunityCreate.builder()
                .category(CATEGORY_BACKEND)
                .title(TITLE)
                .content(CONTENT)
                .isHighlighted(false)
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
    }

    private CommunityResponse makeCommunityResponse(MemberEntity mockMember, CategoryEntity mockCategory) {
        return CommunityResponse.builder()
                .sequence(10L)
                .memberSequence(mockMember.getSequence())
                .nickname(mockMember.getNickname())
                .category(mockCategory.getName())
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