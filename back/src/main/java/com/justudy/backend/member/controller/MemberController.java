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

        validMember(request);

    /**
     * 마이페이지 멤버 정보 API
     * @param HttpSession session에서 memberSequence를 찾기 위해
     * @return MypageRespoonse 마이페이지 멤버 응답 객체
     */
    @GetMapping("/mypage/member")
    public MypageResponse mypageMember(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return memberService.getMemberOfMypage(loginSequence);
    }

    private void validMember(MemberCreate request) {
        if (memberService.isDuplicatedUserId(request.getUserId())) {
            throw new ConflictRequest("userId", "이미 가입된 아이디입니다.");
        }
        if (memberService.isDuplicatedNickname(request.getNickname())) {
            throw new ConflictRequest("nickname", "이미 가입된 닉네임입니다.");
        }
        if (memberService.isDuplicatedSsafyId(request.getSsafyId())) {
            throw new ConflictRequest("ssafyId", "이미 가입된 SSAFY학번입니다.");
        }
    }
}
