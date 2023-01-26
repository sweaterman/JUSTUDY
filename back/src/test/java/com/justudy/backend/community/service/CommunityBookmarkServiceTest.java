package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunityBookmarkCreate;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.response.CommunityResponse;
import com.justudy.backend.community.repository.CommunityBookmarkRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CommunityBookmarkServiceTest {

    Logger log;
    @Autowired
    private CommunityRepository repository;
    @Autowired
    private CommunityBookmarkRepository bookmarkRepository;
    @Autowired
    private CommunityBookmarkService service;
    @Autowired
    private CommunityService communityService;

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    private final String USER_ID = "justudy";
    private final String NICKNAME = "levi";
    private final String SSAFY_ID = "0847968";
    MemberEntity findMember;
    Optional<CommunityEntity> findCommunity;

    @Transactional
    @BeforeEach
    public void setUp() {
        log = (Logger) LoggerFactory.getLogger(CommunityRepository.class);
        log.info("정보1 : end set up->{}", findMember);
        MemberCreate memberRequest = makeMemberCreateBuilder()
                .userId(USER_ID)
                .nickname(NICKNAME)
                .ssafyId(SSAFY_ID)
                .build();
        Long savedMemberId = memberService.saveMember(memberRequest, null);
        findMember = memberRepository.findById(savedMemberId).get();

        Long savedCommunityId = communityService.createCommunity(CommunityCreate
                .builder()
                .member(findMember)
                .category_seq(33L)
                .title("title")
                .content("내용")
                .createdTime(LocalDateTime.now())
                .build()
        );
        findCommunity = repository.findById(savedCommunityId);
        log.info("정보2 : end set up->{},{}", findMember, findCommunity);

    }

    @Transactional
    @Test
    void createBookmark() {
        // Given

        CommunityBookmarkCreate create = makeRequest(findMember, findCommunity.get());
        Assertions.assertThat(communityService.readAllCommunity(0, "33").size()).isEqualTo(1);
//        log.info("정보3 ->{}", communityService.readAllCommunity(0, "33").get(0).getMember().getSequence());
        // When
        service.createBookmark(create);
        service.createBookmark(create);

        // Then
        Assertions.assertThat(service.readAllBookmarkByMember(1L).size()).isEqualTo(1);
    }


    @Transactional
    @Test
    void deleteBookmark() {     // Given
        CommunityBookmarkCreate create = makeRequest(findMember, findCommunity.get());
        // When
        service.createBookmark(create);
        Assertions.assertThat(service.readAllBookmarkByMember(1L).size()).isEqualTo(1);
        service.deleteBookmark(2L, 1L);

        // Then
        Assertions.assertThat(service.readAllBookmarkByMember(1L).size()).isEqualTo(0);

    }

    @Transactional
    @Test
    void readAllBookmarkByMember() {
        // Given
        CommunityBookmarkCreate create = makeRequest(findMember, findCommunity.get());
        //2번째 커뮤니티 생성
        Long savedCommunityId2 = communityService.createCommunity(CommunityCreate
                .builder()
                .member(findMember)
                .category_seq(33L)
                .title("title")
                .content("내용")
                .createdTime(LocalDateTime.now())
                .build()
        );
        Optional<CommunityEntity> findCommunity2 = repository.findById(savedCommunityId2);

        CommunityBookmarkCreate create2 = makeRequest(findMember, findCommunity2.get());
        List<CommunityResponse> list = communityService.readAllCommunity(0, "33");

        // When
        service.createBookmark(create);
        service.createBookmark(create2);


        // Then
        Assertions.assertThat(service.readAllBookmarkByMember(1L).size()).isEqualTo(2);
    }

    private CommunityBookmarkCreate makeRequest(MemberEntity findMember, CommunityEntity findCommunity) {
        return CommunityBookmarkCreate
                .builder()
                .community(findCommunity)
                .member(findMember)
                .build();
    }

    private MemberCreate.MemberCreateBuilder makeMemberCreateBuilder() {
        return MemberCreate.builder()
                .password("1234")
                .passwordCheck("1234")
                .username("이싸피")
                .phone("01051391111")
                .email("ssafylee@ssafy.com")
                .region("SEOUL")
                .dream("백엔드취업 희망")
                .category(new String[]{"JAVA", "Spring", "JPA"})
                .introduction("test.");
    }
}