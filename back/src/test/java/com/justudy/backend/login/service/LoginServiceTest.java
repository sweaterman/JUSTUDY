package com.justudy.backend.login.service;

import com.justudy.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("로그인 서비스")
    void test() {

    }
}