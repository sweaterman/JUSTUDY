package com.justudy.backend.member.controller;

import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
import com.justudy.backend.member.dto.response.MypageResponse;
import com.justudy.backend.member.exception.ConflictRequest;
import com.justudy.backend.member.exception.InvalidRequest;
import com.justudy.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<Void> signupMember(@RequestBody @Validated MemberCreate request) {
        validMember(request);
        memberService.saveMember(request);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

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

    @GetMapping("/mypage/modify")
    public ModifyPageResponse memberModifyPage(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return memberService.getModifyPage(loginSequence);
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
        if (memberService.isNotEqualPassword(request.getPassword(), request.getPasswordCheck())) {
            throw new InvalidRequest("password", "비밀번호와 비밀번호확인이 다릅니다.");
        }
    }
}
