package com.justudy.backend.login.service;

import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.login.dto.request.LoginRequest;
import com.justudy.backend.login.dto.response.LoginResponse;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class LoginServiceTest {

    LoginService loginService;

    MemberRepository memberRepository;

    private final String USER_ID = "testId";
    private final String PASSWORD = "qwerty";
    private final String WRONG_USER_ID = "ssafy";
    private final String WRONG_PASSWORD = "1234";

    @Test
    @DisplayName("로그인 서비스")
    @Transactional
    void loginComplete() {
        //given
        MemberEntity member = makeTestMember(USER_ID, PASSWORD);
        memberRepository.save(member);

        //when
        LoginResponse loginResponse = loginService.loginProcess(new LoginRequest(USER_ID, PASSWORD));
        MemberEntity findMember = memberRepository.findById(loginResponse.getLoginSequence()).get();

        //then
        assertThat(findMember.getUserId()).isEqualTo(USER_ID);
        assertThat(findMember.getPassword()).isEqualTo(PASSWORD);
    }

    @Test
    @DisplayName("로그인 - 아이디가 없을 때")
    @Transactional
    void loginFailWithUserId() {
        //given
        MemberEntity member = makeTestMember(USER_ID, PASSWORD);
        memberRepository.save(member);

        //expected
        assertThatThrownBy(() -> loginService.loginProcess(new LoginRequest(WRONG_USER_ID, PASSWORD)))
                .isInstanceOf(InvalidRequest.class)
                .hasMessage("잘못된 요청입니다.");
    }

    @Test
    @DisplayName("로그인 - 비밀번호가 틀릴 때")
    @Transactional
    void loginFailWithPassword() {
        //given
        MemberEntity member = makeTestMember(USER_ID, PASSWORD);
        memberRepository.save(member);

        //expected
        assertThatThrownBy(() -> loginService.loginProcess(new LoginRequest(USER_ID, WRONG_PASSWORD)))
                .isInstanceOf(InvalidRequest.class)
                .hasMessage("잘못된 요청입니다.");
    }


    private MemberEntity makeTestMember(String userId, String password) {
        return MemberEntity.builder()
                .userId(userId)
                .password(password)
                .username("이신광")
                .nickname("nickname")
                .ssafyId("0841231")
                .phone("01011111111")
                .email("ssafylee@ssafy.com")
                .region(Region.SEOUL)
                .dream("백엔드 취업희망")
                .introduction("안녕하세요")
                .build();
    }
}