package com.justudy.backend.member.controller;

import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.dto.response.FollowResponse;
import com.justudy.backend.member.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{memberSequence}")
    public ResponseEntity<Void> followMember(@PathVariable Long memberSequence,
                                       HttpSession session) {

        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        followService.saveFollow(loginSequence, memberSequence);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{memberSequence}")
    public ResponseEntity<Void> deleteFollow(@PathVariable Long memberSequence,
                                             HttpSession session) {

        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        followService.deleteFollow(loginSequence, memberSequence);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/my-follow")
    public List<FollowResponse> getMyFollow(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return followService.getMyFollow(loginSequence);
    }

    @GetMapping("/my-follower")
    public List<FollowResponse> getMyFollower(HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return followService.getMyFollower(loginSequence);
    }
}
