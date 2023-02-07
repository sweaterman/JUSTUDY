//package com.justudy.backend.community.service;
//
//import com.justudy.backend.community.domain.CommunityEntity;
//import com.justudy.backend.community.dto.request.CommunityCommentCreate;
//import com.justudy.backend.community.dto.request.CommunityCreate;
//import com.justudy.backend.community.dto.request.CommunityLoveCreate;
//import com.justudy.backend.community.dto.response.CommunityCommentResponse;
//import com.justudy.backend.community.repository.CommunityCommentRepository;
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
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class CommunityCommentServiceTest {
//
//    Logger log;
//    @Autowired
//    private CommunityRepository repository;
//    @Autowired
//    private CommunityCommentRepository commentRepository;
//    @Autowired
//    private CommunityService communityService;
//    @Autowired
//    private CommunityCommentService service;
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MemberRepository memberRepository;
//    private final String USER_ID = "justudy";
//    private final String NICKNAME = "levi";
//    private final String SSAFY_ID = "0847968";
//    MemberEntity findMember;
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
//        log.info("정보2 : end set up->{},{}", savedMemberId, savedMemberId);
//    }
//
//    @Transactional
//    @Test
//    void createComment() {
//        // Given
//        CommunityCommentCreate create = makeRequest(findMember, findCommunity.get(), 0L);
////        Assertions.assertThat(service.createComment(2L,create).getSequence()).isEqualTo(4L);
//        // When
//        service.createComment(2L, create);
//        //같은내용 생성시  CommunityLoveAlreadyCreated
////        service.createLove(create);
//
//        // Then
//        Assertions.assertThat(service.readAllComment(2L).size()).isEqualTo(1);
//
//    }
//
//    @Transactional
//    @Test
//    void readComment() {
//        // Given
//        CommunityCommentCreate create = makeRequest(findMember, findCommunity.get(), 0L);
//        // When
//
//        Long commentId = service.createComment(2L, create).getSequence();
//
//        // Then
//        Assertions.assertThat(service.readComment(commentId).getSequence()).isEqualTo(commentId);
//    }
//
//    @Transactional
//    @Test
//    void readAllComment() {
//        // Given
//        //3
//        //  6
//        //  7
//        //      9
//        //      10
//        //  8
//        //  11
//        //      12
//        //4
//        //5
//        Long[] given = {0L, 0L, 0L, 3L, 3L, 3L, 7L, 7L, 3L, 11L};
////        Long[] given = {0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L};
//        for (int i = 0; i < 10; i++) {
//            CommunityCommentCreate create = makeRequest(findMember, findCommunity.get(), given[i]);
//            log.info("정보3 {},{}", i, create.getParentSeq());
//            service.createComment(2L, create);
//        }
//        // When
//        Long[] test = {3L, 6L, 7L, 9L, 10L, 8L, 11L, 12L, 4L, 5L};
//        List<CommunityCommentResponse> list = service.readAllComment(2L);
//        // Then
//        for (int i = 0; i < 10; i++) {
//
//            log.info("정보4 {},{}", list.get(i).getSequence(), list.get(i).getParentSeq());
////            Assertions.assertThat(list.get(i).getSequence()).isEqualTo(test[i]);
//        }
//    }
//
//    private CommunityCommentCreate makeRequest(MemberEntity findMember, CommunityEntity findCommunity, Long parentSeq) {
//        return CommunityCommentCreate
//                .builder()
//                .community(findCommunity)
//                .member(findMember)
//                .content("reply test")
//                .parentSeq(parentSeq)
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
//                .category(new String[]{"Java", "Spring"})
//                .introduction("test.");
//    }
//}