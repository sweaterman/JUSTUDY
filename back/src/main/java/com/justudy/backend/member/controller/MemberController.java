package com.justudy.backend.member.controller;

import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.request.MemberEdit;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
import com.justudy.backend.member.dto.response.MypageResponse;
import com.justudy.backend.member.dto.response.ProfileResponse;
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
        memberService.saveMember(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 마이페이지 멤버 정보 API
     * @param session session에서 memberSequence를 찾기 위해
     * @return MypageRespoonse 마이페이지 멤버 응답 객체
     */
    @GetMapping("/mypage/member")
    public MypageResponse getMypageInfomation(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return memberService.getMypage(loginSequence);
    }

    @GetMapping("/mypage/modify")
    public ModifyPageResponse getModifyPageInformation(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return memberService.getModifyPage(loginSequence);
    }

    @PatchMapping("/mypage/modify")
    public ResponseEntity<Void> modifyMember(@RequestBody @Validated MemberEdit request, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        memberService.editMember(loginSequence, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/profiles/{memberSequence}")
    public ProfileResponse getProfile(@PathVariable Long memberSequence) {
        return memberService.getProfile(memberSequence);
    }

    @DeleteMapping("/mypage/delete")
    public ResponseEntity<Void> quitMember(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        memberService.deleteMember(loginSequence);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * ADMIN 유저의 회원 삭제 API
     * @param memberSequence - Target Member Sequence
     * @param session
     *
     */
    @DeleteMapping("/admin/members/{memberSequence}")
    public ResponseEntity<Void> banMember(@PathVariable Long memberSequence, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        memberService.banMember(loginSequence, memberSequence);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
