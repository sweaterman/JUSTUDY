package com.justudy.backend.community.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.response.CommunityDetailResponse;
import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.community.dto.response.CommunityListResult;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.exception.ForbiddenRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

class CommunityServiceTest {

    private CommunityRepository communityRepository = Mockito.mock(CommunityRepository.class);
    private CommunityBookmarkService bookmarkService = Mockito.mock(CommunityBookmarkService.class);
    private CommunityLoveService loveService = Mockito.mock(CommunityLoveService.class);
    private MemberService memberService = Mockito.mock(MemberService.class);
    private CategoryService categoryService = Mockito.mock(CategoryService.class);
    private CommunityService communityService;

    private final String CATEGORY_KEY = "backend";
    private final String CATEGORY_VALUE = "백엔드";
    private final String TITLE = "테스트제목";
    private final String CONTENT = "테스트내용";


    @BeforeEach
    void setUp() {
        communityService = new CommunityService(communityRepository,
                categoryService,
                bookmarkService,
                loveService
        );
    }

    @Test
    @DisplayName("게시글 라스트 조회 - 공지글 있을 시")
    void getCommunities() {
        //given
        final Long PAGE = 1L;
        final Long SIZE = 20L;
        CommunitySearch condition = CommunitySearch.builder()
                .page(PAGE)
                .size(SIZE)
                .build();
        List<CommunityEntity> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CommunityEntity community = new CommunityEntity("공지제목" + i, "공지내용" + i, true);
            ReflectionTestUtils.setField(community, "member", makeTestMember(i + "", i + "", i + ""));
            list.add(community);
        }
        for (int i = 0; i < 40; i++) {
            CommunityEntity community = new CommunityEntity("제목" + i, "내용" + i, false);
            ReflectionTestUtils.setField(community, "member", makeTestMember(i + "", i + "", i + ""));
            list.add(community);
        }

        long size = SIZE;
        BDDMockito.given(communityRepository.getAllList(condition))
                .willReturn(list.subList(0, (int) size));
        BDDMockito.given(communityRepository.getCountOfList(condition))
                .willReturn(40L);
        for (long i = 0; i < 20; i++) {
            BDDMockito.given(loveService.getCountOfLove(i))
                    .willReturn((int) i);
        }

        //when
        CommunityListResult<List<CommunityListResponse>> result = communityService.getCommunities(condition);

        //then
        assertThat(result.getCommunityList().size()).isEqualTo(size);
        for (int i = 0; i < 3; i++) {
            assertThat(result.getCommunityList().get(i).isHighlighted()).isTrue();
        }
        for (int i = 3; i < result.getCommunityList().size(); i++) {
            assertThat(result.getCommunityList().get(i).isHighlighted()).isFalse();
        }
        assertThat(result.getTotalCount()).isEqualTo(40L);
    }

    @Test
    @DisplayName("게시글 라스트 조회 - 공지글 없을 시")
    void getCommunitiesWithoutNotice() {
        //given
        final Long PAGE = 1L;
        final Long SIZE = 20L;
        CommunitySearch condition = CommunitySearch.builder()
                .page(PAGE)
                .size(SIZE)
                .build();
        List<CommunityEntity> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            CommunityEntity community = new CommunityEntity("제목" + i, "내용" + i, false);
            ReflectionTestUtils.setField(community, "member", makeTestMember(i + "", i + "", i + ""));
            list.add(community);
        }
        long size = SIZE;
        BDDMockito.given(communityRepository.getAllList(condition))
                .willReturn(list.subList(0, (int) size));
        BDDMockito.given(communityRepository.getCountOfList(condition))
                .willReturn(40L);
        for (long i = 0; i < 20; i++) {
            BDDMockito.given(loveService.getCountOfLove(i))
                    .willReturn((int) i);
        }

        //when
        CommunityListResult<List<CommunityListResponse>> result = communityService.getCommunities(condition);

        //then
        assertThat(result.getCommunityList().size()).isEqualTo(size);
        for (int i = 0; i < result.getCommunityList().size(); i++) {
            assertThat(result.getCommunityList().get(i).isHighlighted()).isFalse();
        }
        assertThat(result.getTotalCount()).isEqualTo(40L);
    }

    @Test
    @DisplayName("게시글 생성")
    @Transactional
    void createCommunity() {
        //given
        CommunityCreate request = CommunityCreate.builder()
                .category(CATEGORY_KEY)
                .title(TITLE)
                .content(CONTENT)
                .build();

        MemberEntity mockMember = makeTestMember("test", "test", "test");
        CategoryEntity mockCategory = new CategoryEntity(CATEGORY_KEY, CATEGORY_VALUE, 0L, null);


        CommunityEntity community = request.toEntity();
        community.addMember(mockMember);
        community.changeCategory(mockCategory);
        ReflectionTestUtils.setField(community, "sequence", 1L);

        BDDMockito.given(memberService.getMember(1L))
                .willReturn(mockMember);
        BDDMockito.given(categoryService.getCategoryEntityByKey(CATEGORY_KEY))
                .willReturn(mockCategory);
        BDDMockito.given(communityRepository.save(any(CommunityEntity.class)))
                .willReturn(community);
        BDDMockito.given(communityRepository.findById(anyLong()))
                .willReturn(Optional.of(community));


        //when
        CommunityDetailResponse response = communityService.createCommunity(request,
                memberService.getMember(1L),
                categoryService.getCategoryEntityByKey(CATEGORY_KEY));

        //then
        assertThat(response.getTitle()).isEqualTo(TITLE);
        assertThat(response.getContent()).isEqualTo(CONTENT);
        assertThat(response.getCategory().getKey()).isEqualTo(mockCategory.getKey());
        assertThat(response.getCategory().getValue()).isEqualTo(mockCategory.getValue());
        assertThat(response.getNickname()).isEqualTo(mockMember.getNickname());
        assertThat(response.getViewCount()).isEqualTo(0);
    }

    @Transactional
    @Test
    @DisplayName("게시글 삭제")
    void deleteCommunity() {
        //given
        MemberEntity mockMember = makeTestMember("testId", "testNickname", "testSsafyId");
        ReflectionTestUtils.setField(mockMember, "sequence", 10L);
        CommunityEntity community = makeTestCommunity();
        ReflectionTestUtils.setField(community, "member", mockMember);
        ReflectionTestUtils.setField(community, "sequence", 100L);

        BDDMockito.given(memberService.getMember(ArgumentMatchers.anyLong()))
                .willReturn(mockMember);
        BDDMockito.given(communityRepository.findBySequence(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(community));

        //when
        Long deletedCommunity = communityService.deleteCommunity(10L, 100L);

        //then
        Assertions.assertThat(deletedCommunity).isEqualTo(100L);
    }

    @Transactional
    @Test
    @DisplayName("없는 게시글을 삭제할 때 - CommunityNotFound 에러")
    void deleteCommunityWithNotFound() {
        //given
        final Long RIGHT_SEQUENCE = 10L;
        final Long WRONG_SEQUENCE = 100L;

        MemberEntity mockMember = makeTestMember("testId", "testNickname", "testSsafyId");
        ReflectionTestUtils.setField(mockMember, "sequence", 5L);
        CommunityEntity community = makeTestCommunity();
        ReflectionTestUtils.setField(community, "sequence", RIGHT_SEQUENCE);
        ReflectionTestUtils.setField(community, "member", mockMember);

        BDDMockito.given(communityRepository.findBySequence(10L))
                .willReturn(Optional.of(community));

        //expected
        assertThatThrownBy(() -> communityService.deleteCommunity(RIGHT_SEQUENCE, WRONG_SEQUENCE))
                .isInstanceOf(CommunityNotFound.class);
    }

    @Transactional
    @Test
    @DisplayName("다른 사람의 게시글 삭제 - 접근권한 에러")
    void deleteCommunityWithForbiddenException() {
        //given
        final Long LOGIN_SEQUENCE = 1L;
        final Long OTHER_MEMBER_SEQUENCE = 10L;

        MemberEntity mockMember = makeTestMember("testId", "testNickname", "testSsafyId");
        ReflectionTestUtils.setField(mockMember, "sequence", OTHER_MEMBER_SEQUENCE);
        CommunityEntity community = makeTestCommunity();
        ReflectionTestUtils.setField(community, "member", mockMember);

        BDDMockito.given(memberService.getMember(ArgumentMatchers.anyLong()))
                .willReturn(mockMember);
        BDDMockito.given(communityRepository.findBySequence(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(community));

        //expected
        assertThatThrownBy(() -> communityService.deleteCommunity(LOGIN_SEQUENCE, 2L))
                .isInstanceOf(ForbiddenRequest.class)
                .hasMessage("접근 권한이 없습니다.");
    }

    @Test
    @DisplayName("게시글 업데이트")
    void updateCommunity() {
        //given
        final Long LOGIN_SEQUENCE = 10L;
        final Long COMMUNITY_SEQUENCE = 100L;
        final String NEW_TITLE = "테스트제목";
        final String NEW_CONTENT = "테스트내용";
        final String NEW_CATEGORY_KEY = "frontend";
        final String NEW_CATEGORY_VALUE = "프론트엔드";

        MemberEntity mockMember = makeTestMember("testId", "testNickname", "testSsafyId");
        ReflectionTestUtils.setField(mockMember, "sequence", LOGIN_SEQUENCE);
        CommunityEntity community = makeTestCommunity();
        ReflectionTestUtils.setField(community, "member", mockMember);

        BDDMockito.given(memberService.getMember(ArgumentMatchers.anyLong()))
                .willReturn(mockMember);
        BDDMockito.given(communityRepository.findBySequence(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(community));
        BDDMockito.given(categoryService.getCategoryEntityByKey(NEW_CATEGORY_KEY))
                .willReturn(new CategoryEntity(NEW_CATEGORY_KEY, NEW_CATEGORY_VALUE, 0L, null));
//        BDDMockito.given(loveRepository.readLoveCountByCommunity(ArgumentMatchers.anyLong()))
//                .willReturn(10);

        //when
        CommunityDetailResponse response = communityService.updateCommunity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE, new CommunityEdit(NEW_TITLE, NEW_CONTENT, NEW_CATEGORY_KEY));

        //then
        assertThat(response.getTitle()).isEqualTo(NEW_TITLE);
        assertThat(response.getContent()).isEqualTo(NEW_CONTENT);
        assertThat(response.getCategory().getKey()).isEqualTo(NEW_CATEGORY_KEY);
        assertThat(response.getCategory().getValue()).isEqualTo(NEW_CATEGORY_VALUE);
    }

    @Test
    @DisplayName("게시글 업데이트 - 로그인 유저와 글쓴이가 다를 때")
    void updateCommunityWithForbiddenException() {
        //given
        final Long LOGIN_SEQUENCE = 10L;
        final Long OTHER_SEQUENCE = 50L;
        final Long COMMUNITY_SEQUENCE = 100L;
        final String NEW_TITLE = "테스트제목";
        final String NEW_CONTENT = "테스트내용";
        final String NEW_CATEGORY_KEY = "frontend";
        final String NEW_CATEGORY_VALUE = "프론트엔드";

        MemberEntity mockMember = makeTestMember("testId", "testNickname", "testSsafyId");
        ReflectionTestUtils.setField(mockMember, "sequence", OTHER_SEQUENCE);
        CommunityEntity community = makeTestCommunity();
        ReflectionTestUtils.setField(community, "member", mockMember);

        BDDMockito.given(memberService.getMember(ArgumentMatchers.anyLong()))
                .willReturn(mockMember);
        BDDMockito.given(communityRepository.findBySequence(ArgumentMatchers.anyLong()))
                .willReturn(Optional.of(community));
        BDDMockito.given(categoryService.getCategoryEntityByKey(NEW_CATEGORY_KEY))
                .willReturn(new CategoryEntity(NEW_CATEGORY_KEY, NEW_CATEGORY_VALUE, 0L, null));
//        BDDMockito.given(loveRepository.readLoveCountByCommunity(ArgumentMatchers.anyLong()))
//                .willReturn(10);

        //expected
        assertThatThrownBy(() -> communityService.updateCommunity(LOGIN_SEQUENCE,
                COMMUNITY_SEQUENCE,
                new CommunityEdit(NEW_TITLE, NEW_CONTENT, NEW_CATEGORY_KEY)))
                .isInstanceOf(ForbiddenRequest.class);
    }

    private CommunityEntity makeTestCommunity() {
        return CommunityEntity.builder()
                .title(TITLE)
                .content(CONTENT)
                .isHighlighted(false)
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