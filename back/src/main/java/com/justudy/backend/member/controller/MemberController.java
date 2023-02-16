package com.justudy.backend.member.controller;

import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.infra.ImageConst;
import com.justudy.backend.file.service.UploadFileService;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.dto.request.MMValidateRequest;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.dto.request.MemberEdit;
import com.justudy.backend.member.dto.response.ModifyPageResponse;
import com.justudy.backend.member.dto.response.MypageResponse;
import com.justudy.backend.member.dto.response.ProfileResponse;
import com.justudy.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    private final UploadFileService uploadFileService;

    @PostMapping("/register")
    public ResponseEntity<Void> signupMember(@RequestBody @Validated MemberCreate request) {
        UploadFileEntity basicImage = uploadFileService.getUploadFile(ImageConst.BASIC_MEMBER_IMAGE);//기본 이미지 파일, 1L
        memberService.saveMember(request, basicImage);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/check")
    public ResponseEntity<Void> validateMember(@RequestParam(name = "userid", required = false) String userId,
                                               @RequestParam(name = "nickname", required = false) String nickname,
                                               @RequestParam(name = "ssafyid", required = false) String ssafyId
                                               ) {

        log.info("userId = {}", userId);
        log.info("nickname = {}", nickname);
        log.info("ssafyId = {}", ssafyId);
        validateParameter(userId, nickname, ssafyId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/edit-check")
    public ResponseEntity<Void> validateEditMember(@RequestParam(name = "nickname") String nickname,
                                                   HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        memberService.validEditNickname(loginSequence, nickname);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/matter-most")
    public ResponseEntity<?> validMatterMost(@RequestBody MMValidateRequest request) {
        return memberService.validateMatterMost(request.getMmId(), request.getMmPassword());
    }

    private void validateParameter(String userId, String nickname, String ssafyId) {
        if (userId != null) {
            memberService.isDuplicatedUserId(userId);
        }
        if (nickname != null) {
            memberService.isDuplicatedNickname(nickname);
        }
        if (ssafyId != null) {
            memberService.isDuplicatedSsafyId(ssafyId);
        }
    }

    /**
     * 마이페이지 멤버 정보 API
     * @param session session에서 memberSequence를 찾기 위해
     * @return MypageRespoonse 마이페이지 멤버 응답 객체
     */
    @GetMapping("/mypage")
    public MypageResponse getMypageInformation(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return memberService.getMypage(loginSequence);
    }

    @GetMapping("/bookmarks")
    public List<CommunityListResponse> getMyBookmarks(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return memberService.getMyBookmarks(loginSequence);
    }

    @GetMapping("/loves")
    public List<CommunityListResponse> getMyLoves(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return memberService.getMyLoves(loginSequence);
    }

    @GetMapping("/mypage/modify")
    public ModifyPageResponse getModifyPageInformation(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return memberService.getModifyPage(loginSequence);
    }

    @PatchMapping(value = "/mypage/modify",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> modifyMember(@RequestPart(name = "request") @Validated MemberEdit request,
                                             @RequestPart(name = "file", required = false) MultipartFile multipartFile,
                                             HttpSession session) throws IOException {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        memberService.editMember(loginSequence, request, multipartFile);
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
}
