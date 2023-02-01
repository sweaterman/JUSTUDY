//package com.justudy.backend.community.service;
//
//import com.justudy.backend.community.domain.CommunityEntity;
//import com.justudy.backend.community.dto.request.CommunityCreate;
//import com.justudy.backend.community.dto.request.CommunityEdit;
//import com.justudy.backend.community.dto.response.CommunityResponse;
//import com.justudy.backend.community.repository.CommunityLoveRepository;
//import com.justudy.backend.community.repository.CommunityRepository;
//import com.justudy.backend.member.domain.MemberEntity;
//import com.justudy.backend.member.dto.request.MemberCreate;
//import com.justudy.backend.member.repository.MemberRepository;
//import com.justudy.backend.member.service.MemberService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//class CommunityServiceIntegrationTest {
//
//    Logger log;
//    @Autowired
//    private CommunityRepository repository;
//    @Autowired
//    private CommunityLoveRepository communityLoveRepository;
//    @Autowired
//    private CommunityService service;
//    private Pageable pageable;
//
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MemberRepository memberRepository;
//    private final String USER_ID = "justudy";
//    private final String NICKNAME = "levi";
//    private final String SSAFY_ID = "0847968";
//    MemberEntity findMember;
//
//    @Transactional
//    @BeforeEach
//    public void setUp() {
//        log = (Logger) LoggerFactory.getLogger(CommunityRepository.class);
//        pageable = PageRequest.of(0, 10);
//        service = new CommunityService(repository, communityLoveRepository);
//        log.info("정보1 : end set up->{}", findMember);
//        MemberCreate memberRequest = makeMemberCreateBuilder()
//                .userId(USER_ID)
//                .nickname(NICKNAME)
//                .ssafyId(SSAFY_ID)
//                .build();
//        Long savedMemberId = memberService.saveMember(memberRequest, null);
//        findMember = memberRepository.findById(savedMemberId).get();
//        log.info("정보2 : end set up->{}", findMember);
//    }
//
//    @Transactional
//    @Test
//    void createCommunity() {
//        // Given
//        CommunityCreate create = makeRequest(findMember);
//
//        // When
//        Long after = service.createCommunity(create, findMember);
//        CommunityEntity savedBoard = repository.findById(after).get();
//
//        // Then
//        assertThat(savedBoard.getTitle()).isEqualTo("title");
//        assertThat(savedBoard.getContent()).isEqualTo("내용");
//    }
//
//    @Transactional
//    @Test
//    void readCommunity() {
//        // Given
//        CommunityCreate create = makeRequest(findMember);
//
//        // When
//        service.createCommunity(create, findMember);
//        CommunityResponse response = service.readCommunity(2L);
//
//        // Then
//        assertThat(response.getSequence()).isEqualTo(2L);
//        assertThat(response.getViewCount()).isEqualTo(1);
//    }
//
//    @Transactional
//    @Test
//    void readAllCommunity() {
//        // Given
//        CommunityCreate create = makeRequest(findMember);
//        CommunityCreate create2 = makeNotice();
//
//        // When
//        for (int i = 0; i < 4; i++) {
//            service.createCommunity(create2, findMember);
//        }
//        for (int i = 0; i < 20; i++) {
//            service.createCommunity(create, findMember);
//        }
//
//        // Then
//        List<CommunityResponse> list = service.readAllCommunity(0, "3");
//        for (int i = 0; i < list.size(); i++) {
//            log.info("정보3 : ->{}", list.get(i).getTitle());
//        }
//        assertThat(service.readAllCommunity(0, "3").size()).isEqualTo(10);
//    }
//
//    @Transactional
//    @Test
//    void updateCommunity() {
//        // Given
//        CommunityCreate create = makeRequest(findMember);
//
//        // When
//        service.createCommunity(create, findMember);
//        service.updateCommunity(2L, makeEditRequest());
//
//        // Then
//        assertThat(service.readCommunity(2L).getTitle()).isEqualTo("update");
//
//    }
//
//    @Transactional
//    @Test
//    void deleteCommunity() {        // Given
//        CommunityCreate create = makeRequest(findMember);
//
//        // When
//        service.createCommunity(create, findMember);
//        service.deleteCommunity(2L);
//
//        // Then
//        assertThat(service.readAllCommunity(0, "3").size()).isEqualTo(0);
//    }
//
//    @Transactional
//    @Test
//    void readAllNoticeCommunity() {
//        // Given
//        CommunityCreate create = makeNotice();
//
//        // When
//        service.createCommunity(create, findMember);
//        service.createCommunity(create, findMember);
//
//        // Then
//        assertThat(service.readAllNoticeCommunity(0).size()).isEqualTo(2);
//
//    }
//
//
//    @Transactional
//    @Test
//    void search() {
//        // Given
//        CommunityCreate create = makeRequest(findMember);
//
//        // When
//        Long after = service.createCommunity(create, findMember);
//
//        // Then
//        assertThat(service.search(0, "name", "levi").size()).isEqualTo(1);
//        assertThat(service.search(0, "title", "tit").size()).isEqualTo(1);
//        assertThat(service.search(0, "content", "내용").size()).isEqualTo(1);
//        assertThat(service.search(0, "name", "김싸피").size()).isEqualTo(0);
//        assertThat(service.search(0, "title", "t2").size()).isEqualTo(0);
//        assertThat(service.search(0, "content", "제목").size()).isEqualTo(0);
//
//    }
//
//    private CommunityEdit makeEditRequest() {
//        return CommunityEdit
//                .builder()
//                .member(findMember)
//                .category_seq(3L)
//                .title("update")
//                .content("update content")
//                .viewCount(0)
//                .modifiedTime(LocalDateTime.now())
//                .build();
//
//    }
//
//    private CommunityCreate makeNotice() {
//        return CommunityCreate
//                .builder()
//                .category_seq(33L)
//                .title("공지")
//                .content("내용")
//                .createdTime(LocalDateTime.now())
//                .isHighlighted(true)
//                .build();
//    }
//
//    private CommunityCreate makeRequest(MemberEntity findMember) {
//        return CommunityCreate
//                .builder()
//                .category_seq(3L)
//                .title("title")
//                .content("내용")
//                .createdTime(LocalDateTime.now())
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