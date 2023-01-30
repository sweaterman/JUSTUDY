package com.justudy.backend.community.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.repository.CommunityLoveRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

class CommunityServiceTest {

    private CommunityRepository communityRepository = Mockito.mock(CommunityRepository.class);

    private CommunityLoveRepository loveRepository = Mockito.mock(CommunityLoveRepository.class);

    private MemberService memberService = Mockito.mock(MemberService.class);

    private CategoryService categoryService = Mockito.mock(CategoryService.class);

    private CommunityService communityService;

    private final String CATEGORY_BACKEND = "backend";
    private final String TITLE = "테스트제목";
    private final String CONTENT = "테스트내용";


    @BeforeEach
    void setUp() {
        communityService = new CommunityService(communityRepository, loveRepository);
    }

    @Test
    @DisplayName("게시글 생성")
    @Transactional
    void createCommunity() {
        //given
        CommunityCreate request = CommunityCreate.builder()
                .category(CATEGORY_BACKEND)
                .title(TITLE)
                .content(CONTENT)
                .isHighlighted(false)
                .build();

        MemberEntity mockMember = makeTestMember("test", "test", "test");
        CategoryEntity mockCategory = new CategoryEntity("backend", 0L);


        CommunityEntity community = request.toEntity();
        community.addMember(mockMember);
        community.changeCategory(mockCategory);
        ReflectionTestUtils.setField(community, "sequence", 1L);

        BDDMockito.given(memberService.getMember(1L))
                .willReturn(mockMember);
        BDDMockito.given(categoryService.getCategory(CATEGORY_BACKEND))
                .willReturn(mockCategory);
        BDDMockito.given(communityRepository.save(any(CommunityEntity.class)))
                .willReturn(community);
        BDDMockito.given(communityRepository.findById(anyLong()))
                .willReturn(Optional.of(community));


        //when
        Long savedCommunitySequence = communityService.createCommunity(request,
                memberService.getMember(1L),
                categoryService.getCategory(CATEGORY_BACKEND));

        CommunityEntity findCommunity = communityRepository.findById(savedCommunitySequence).get();
        //then
        assertThat(findCommunity.getTitle()).isEqualTo(TITLE);
        assertThat(findCommunity.getContent()).isEqualTo(CONTENT);
        assertThat(findCommunity.getCategory()).isEqualTo(mockCategory);
        assertThat(findCommunity.getMember()).isEqualTo(mockMember);
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