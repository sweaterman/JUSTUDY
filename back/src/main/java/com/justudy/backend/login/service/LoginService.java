package com.justudy.backend.login.service;

import com.justudy.backend.login.dto.request.LoginRequest;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.member.repository.MemberRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.justudy.backend.member.domain.QMemberEntity.*;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public Long loginProcess(LoginRequest loginRequest) {
        Tuple tuple = memberRepository.findPasswordByUserId(loginRequest.getUserId())
                .orElseThrow(() -> new InvalidRequest("userId", "잘못된 아이디입니다."));
        String originPassword = tuple.get(memberEntity.password);
        validatePassword(loginRequest, originPassword);

        return tuple.get(memberEntity.sequence);
    }


    private void validatePassword(LoginRequest request, String encodedPassword) {
        boolean matches = passwordEncoder.matches(request.getPassword(), encodedPassword);

        if (!matches) {
            throw new InvalidRequest("password", "비밀번호가 잘못되었습니다.");
        }
    }

}
