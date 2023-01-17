package com.justudy.backend.member.controller;

import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.exception.ConflictRequest;
import com.justudy.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public void signupMember(@RequestBody @Validated MemberCreate request) {

        if (memberService.isDuplicatedUserId(request.getUserId())) {
            throw new ConflictRequest("userId", "이미 가입된 아이디입니다.");
        }

        memberService.createMember(request);
    }
}
