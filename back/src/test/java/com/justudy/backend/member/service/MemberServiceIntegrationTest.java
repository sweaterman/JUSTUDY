package com.justudy.backend.member.service;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.domain.MemberRegion;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceIntegrationTest {

    private final String USER_ID = "justudy";
    private final String NICKNAME = "levi";
    private final String SSAFY_ID = "0847968";

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    @Test
    @DisplayName("유저 저장")
    void saveMember() {
        //given
        MemberCreate request = makeMemberCreateBuilder()
                .userId(USER_ID)
                .nickname(NICKNAME)
                .ssafyId(SSAFY_ID)
                .build();

        //when
        Long savedMemberId = memberService.saveMember(request);
        MemberEntity findMember = memberRepository.findById(savedMemberId).get();

        //then
        assertThat(findMember.getUserId()).isEqualTo(USER_ID);
        assertThat(findMember.getNickname()).isEqualTo(NICKNAME);
        assertThat(findMember.getSsafyId()).isEqualTo(SSAFY_ID);
        assertThat(findMember.getPassword()).isEqualTo("1234");
        assertThat(findMember.getUsername()).isEqualTo("이싸피");
        assertThat(findMember.getPhone()).isEqualTo("01051391111");
        assertThat(findMember.getRegion()).isEqualTo(MemberRegion.SEOUL);
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
                .introduction("이신광이다.");
    }
}
