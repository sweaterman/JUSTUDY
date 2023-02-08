package com.justudy.backend.chat.controller;

import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.infra.ImageConst;
import com.justudy.backend.file.service.UploadFileService;
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
@RequestMapping("/api/chat")
public class ChatController {

    private final MemberService memberService;

    private final UploadFileService uploadFileService;

    //알림 왔는지 확인
    //내 방리스트 불러오기(맨 위 메시지 불러오기)
    //방안의 메시지 불러오기
    //팔로우 팔로워에게 메시지 보내기
    //둘다인지 체크
    //스터디원에게 알람 보내기
    //방삭제
    //
    @PostMapping("/register")
    public ResponseEntity<Void> createChat(@RequestBody @Validated MemberCreate request) {
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
        return null;
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


    /**
     * ADMIN 유저의 회원 삭제 API
     * @param memberSequence - Target Member Sequence
     * @param session
     */
    @DeleteMapping("/admin/members/{memberSequence}")
    public ResponseEntity<Void> banMember(@PathVariable Long memberSequence, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        memberService.banMember(loginSequence, memberSequence);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}