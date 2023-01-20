package com.justudy.backend.login.service;

import com.justudy.backend.login.dto.request.LoginRequest;
import com.justudy.backend.member.exception.InvalidRequest;
import com.justudy.backend.member.repository.MemberRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public Long loginProcess(LoginRequest loginRequest) {
        Tuple tuple = memberRepository.hasUserId(loginRequest.getUserId())
                .orElseThrow(() -> new InvalidRequest("userId", "잘못된 아이디입니다."));

        System.out.println(tuple.get(1, String.class));

        return null;
    }

}
