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

    /**
     * 회원등록 API
     *
     * @param request 멤버 가입 요청 객체
     */
    @PostMapping("/register")
    public ResponseEntity<Void> signupMember(@RequestBody @Validated MemberCreate request) {
        memberService.saveMember(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 마이페이지 멤버 정보 API
     *
     * @param session session에서 memberSequence를 찾기 위해
     * @return MypageRespoonse 마이페이지 멤버 응답 객체
     */
    @GetMapping("/mypage/member")
    public MypageResponse getMypageInfomation(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return memberService.getMypage(loginSequence);
    }

    /**
     * 회원 정보 수정 페이지 API
     *
     * @param session session에서 memberSequence를 찾기 위해
     * @return ModifyPageResonse 수정페이지 멤버 응답 객체
     */
    @GetMapping("/mypage/modify")
    public ModifyPageResponse getModifyPageInformation(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return memberService.getModifyPage(loginSequence);
    }

    /**
     * 회원정보 수정 API
     *
     * @param request 수정 요청 정보
     * @param session 세션에서 sequence 확인
     */
    @PatchMapping("/mypage/modify")
    public ResponseEntity<Void> modifyMember(@RequestBody @Validated MemberEdit request, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        memberService.editMember(loginSequence, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 프로필 조회 API
     * @param memberSequence
     * @return ProfileResponse 프로필 응답 객체
     */
    @GetMapping("/profiles/{memberSequence}")
    public ProfileResponse getProfile(@PathVariable Long memberSequence) {
        return memberService.getProfile(memberSequence);
    }

    /**
     * 회원 탈퇴 API
     *
     * @param session - memberSequence 확인용
     * 논리적 삭제
     */
    @DeleteMapping("/mypage/delete")
    public ResponseEntity<Void> quitMember(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        memberService.deleteMember(loginSequence);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * ADMIN의 회원 밴 API
     * @param memberSequence - Target Member Sequence
     * @param session
     * 논리적 삭제
     */
    @DeleteMapping("/admin/members/{memberSequence}")
    public ResponseEntity<Void> banMember(@PathVariable Long memberSequence, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        memberService.banMember(loginSequence, memberSequence);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
