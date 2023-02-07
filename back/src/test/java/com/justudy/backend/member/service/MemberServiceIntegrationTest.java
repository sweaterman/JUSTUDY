//package com.justudy.backend.member.service;
//
//import com.justudy.backend.common.enum_util.Region;
//import com.justudy.backend.member.domain.MemberEntity;
//import com.justudy.backend.member.dto.request.MemberCreate;
//import com.justudy.backend.member.dto.request.MemberEdit;
//import com.justudy.backend.member.repository.MemberRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class MemberServiceIntegrationTest {
//
//    private final String USER_ID = "justudy";
//    private final String NICKNAME = "levi";
//    private final String SSAFY_ID = "0847968";
//
//    @Autowired
//    MemberService memberService;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Transactional
//    @Test
//    @DisplayName("유저 저장")
//    void saveMember() {
//        //given
//        MemberCreate request = makeMemberCreateBuilder()
//                .userId(USER_ID)
//                .nickname(NICKNAME)
//                .ssafyId(SSAFY_ID)
//                .build();
//
//        //when
//        Long savedMemberId = memberService.saveMember(request, null);
//        MemberEntity findMember = memberRepository.findById(savedMemberId).get();
//
//        //then
//        assertThat(findMember.getUserId()).isEqualTo(USER_ID);
//        assertThat(findMember.getNickname()).isEqualTo(NICKNAME);
//        assertThat(findMember.getSsafyId()).isEqualTo(SSAFY_ID);
//        assertThat(findMember.getPassword()).isEqualTo("1234");
//        assertThat(findMember.getUsername()).isEqualTo("이싸피");
//        assertThat(findMember.getPhone()).isEqualTo("01051391111");
//        assertThat(findMember.getCategories().size()).isEqualTo(2);
//        assertThat(findMember.getRegion()).isEqualTo(Region.SEOUL);
//    }
//
//    @Transactional
//    @Test
//    @DisplayName("유저 수정")
//    void updateMember() throws IOException {
//        //given
//        final String MODIFIED_NICKNAME = "modified";
//        final String MODIFIED_PASSWORD = "modified";
//        final String MODIFIED_PASSWORDCHECK = "modified";
//        final String MODIFIED_PHONE = "11111111";
//        final String MODIFIED_EMAIL = "modified@modified.com";
//        final String MODIFIED_REGION = "GUMI";
//        final String MODIFIED_DREAM = "프론트엔드 할래";
//        final String MODIFIED_INTRODUCTION = "프론트엔드 초고수다";
//        final String[] MODIFIED_CATEGORY = new String[]{"JavaScript", "React"};
//
//        MemberCreate request = makeMemberCreateBuilder()
//                .userId(USER_ID)
//                .nickname(NICKNAME)
//                .ssafyId(SSAFY_ID)
//                .build();
//
//        Long savedMemberSequence = memberService.saveMember(request, null);
//
//        MemberEdit editRequest = MemberEdit.builder()
//                .nickname(MODIFIED_NICKNAME)
//                .password(MODIFIED_PASSWORD)
//                .passwordCheck(MODIFIED_PASSWORDCHECK)
//                .phone(MODIFIED_PHONE)
//                .email(MODIFIED_EMAIL)
//                .region(MODIFIED_REGION)
//                .dream(MODIFIED_DREAM)
//                .introduction(MODIFIED_INTRODUCTION)
//                .category(MODIFIED_CATEGORY)
//                .build();
//
//        //when
//        Long modifiedMember = memberService.editMember(savedMemberSequence, editRequest, null);
//        MemberEntity findMember = memberRepository.findById(modifiedMember).get();
//
//        //then
//        assertThat(findMember.getNickname()).isEqualTo(MODIFIED_NICKNAME);
//        assertThat(findMember.getPassword()).isEqualTo(MODIFIED_PASSWORD);
//        assertThat(findMember.getPhone()).isEqualTo(MODIFIED_PHONE);
//        assertThat(findMember.getEmail()).isEqualTo(MODIFIED_EMAIL);
//        assertThat(findMember.getRegion().getKey()).isEqualTo(MODIFIED_REGION);
//        assertThat(findMember.getDream()).isEqualTo(MODIFIED_DREAM);
//        assertThat(findMember.getIntroduction()).isEqualTo(MODIFIED_INTRODUCTION);
//
//        List<String> categories = findMember.getCategories().stream()
//                .map(memberCategory -> memberCategory.getCategory().getValue())
//                .collect(Collectors.toList());
//
//        assertThat(categories.contains("JavaScript")).isTrue();
//        assertThat(categories.contains("React")).isTrue();
//
//
//    }
//
//
//
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
//                .introduction("이신광이다.");
//    }
//}
