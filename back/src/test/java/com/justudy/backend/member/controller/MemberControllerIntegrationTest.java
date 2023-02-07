//package com.justudy.backend.member.controller;
//
//import com.justudy.backend.member.domain.MemberEntity;
//import com.justudy.backend.member.dto.request.MemberCreate;
//import com.justudy.backend.member.repository.MemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//public class MemberControllerIntegrationTest {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private MemberController memberController;
//
//    @Test
//    @DisplayName("회원가입 요청")
//    @Transactional
//    void signupMember() {
//        //given
//        MemberCreate request = MemberCreate.builder()
//                .userId("sklee0206")
//                .password("1234")
//                .passwordCheck("1234")
//                .username("이싸피")
//                .nickname("돌로스원숭이")
//                .ssafyId("0847968")
//                .phone("01051391111")
//                .email("ssafylee@ssafy.com")
//                .region("SEOUL")
//                .dream("백엔드취업 희망")
//                .category(new String[]{"JAVA", "Spring"})
//                .introduction("안녕하세요.")
//                .build();
//
//        //when
//        memberController.signupMember(request);
//
//        //then
//        List<MemberEntity> members = memberRepository.findAll();
//        Assertions.assertThat(members.size()).isEqualTo(1);
//    }
//}
