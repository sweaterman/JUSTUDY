//package com.justudy.backend.community.service;
//
//import com.justudy.backend.community.domain.CommunityEntity;
//import com.justudy.backend.community.dto.request.CommunityCreate;
//import com.justudy.backend.community.dto.request.CommunityLoveCreate;
//import com.justudy.backend.community.repository.CommunityLoveRepository;
//import com.justudy.backend.community.repository.CommunityRepository;
//import com.justudy.backend.member.domain.MemberEntity;
//import com.justudy.backend.member.dto.request.MemberCreate;
//import com.justudy.backend.member.repository.MemberRepository;
//import com.justudy.backend.member.service.MemberService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@SpringBootTest
//class CommunityLoveServiceTest {
//
//    Logger log;
//    @Autowired
//    private CommunityRepository repository;
//    @Autowired
//    private CommunityLoveRepository loveRepository;
//    @Autowired
//    private CommunityLoveService service;
//    @Autowired
//    private CommunityService communityService;
//
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MemberRepository memberRepository;
//    private final String USER_ID = "justudy";
//    private final String NICKNAME = "levi";
//    private final String SSAFY_ID = "0847968";
//    MemberEntity findMember;
//    MemberEntity findMember2;
//    Optional<CommunityEntity> findCommunity;
//
//    @Transactional
//    @BeforeEach
//    public void setUp() {
//        log = (Logger) LoggerFactory.getLogger(CommunityRepository.class);
//        log.info("정보1 : end set up->{}", findMember);
//        MemberCreate memberRequest = makeMemberCreateBuilder()
//                .userId(USER_ID)
//                .nickname(NICKNAME)
//                .ssafyId(SSAFY_ID)
//                .build();
//        Long savedMemberId = memberService.saveMember(memberRequest, null);
//        findMember = memberRepository.findById(savedMemberId).get();
//
//        Long savedCommunityId = communityService.createCommunity(CommunityCreate
//                .builder()
//                .category_seq(33L)
//                .title("title")
//                .content("내용")
//                .createdTime(LocalDateTime.now())
//                .build(), findMember
//        );
//        findCommunity = repository.findById(savedCommunityId);
//
//        //2번째 멤버 생성
//        MemberCreate memberRequest2 = makeMemberCreateBuilder()
//                .userId(USER_ID+"test")
//                .nickname(NICKNAME)
//                .ssafyId(SSAFY_ID)
//                .build();
//        Long savedMemberId2 = memberService.saveMember(memberRequest, null);
//        findMember2 = memberRepository.findById(savedMemberId2).get();
//
//
//        log.info("정보2 : end set up->{},{}", savedMemberId,savedMemberId2);
//    }
//
//    @Transactional
//    @Test
//    void createLove() {
//        // Given
//        CommunityLoveCreate create = makeRequest(findMember, findCommunity.get());
//        Assertions.assertThat(communityService.readCommunity(2L).getSequence()).isEqualTo(2L);
//        // When
//        service.createLove(create);
//        //같은내용 생성시  CommunityLoveAlreadyCreated
////        service.createLove(create);
//
//        // Then
//        Assertions.assertThat(service.readAllLoveByCommunity(2L).size()).isEqualTo(1);
//    }
//
//    @Transactional
//    @Test
//    void readAllLoveByCommunity() {
//        // Given
//        CommunityLoveCreate create = makeRequest(findMember, findCommunity.get());
//        CommunityLoveCreate create2 = makeRequest(findMember2, findCommunity.get());
//        // When
//        service.createLove(create);
//        service.createLove(create2);
//        // Then
//        Assertions.assertThat(service.readAllLoveByCommunity(2L).size()).isEqualTo(2);
//    }
//
//    @Transactional
//    @Test
//    void deleteAllLoveByCommunity() {
//        // Given
//        CommunityLoveCreate create = makeRequest(findMember, findCommunity.get());
//        CommunityLoveCreate create2 = makeRequest(findMember2, findCommunity.get());
//        // When
//        service.createLove(create);
//        service.createLove(create2);
//
//        Assertions.assertThat(service.readAllLoveByCommunity(2L).size()).isEqualTo(2);
//        service.deleteAllLoveByCommunity(2L);
//        // Then
//        Assertions.assertThat(service.readAllLoveByCommunity(2L).size()).isEqualTo(0);
//
//
//    }
//
//    @Test
//    void updateLove() {
//
//    }
//
//    private CommunityLoveCreate makeRequest(MemberEntity findMember, CommunityEntity findCommunity) {
//        return CommunityLoveCreate
//                .builder()
//                .community(findCommunity)
//                .member(findMember)
//                .build();
//    }
//
//    private MemberCreate.MemberCreateBuilder makeMemberCreateBuilder() {
//        return MemberCreate.builder()
//                .password("1234")
//                .passwordCheck("1234")
//                .username("이싸피")
//                .phone("01051391111")
//                .email("ssafylee@ssafy.com")
//                .region("SEOUL")
//                .dream("백엔드취업 희망")
//                .category(new String[]{"JAVA", "Spring"})
//                .introduction("test.");
//    }
//}