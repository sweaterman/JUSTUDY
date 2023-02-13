package com.justudy.backend.admin.controller;

import com.justudy.backend.admin.dto.request.MemberSearch;
import com.justudy.backend.admin.dto.response.AdminMemberDetail;
import com.justudy.backend.admin.dto.response.MemberListResponse;
import com.justudy.backend.admin.dto.response.MemberListResult;
import com.justudy.backend.admin.dto.response.TotalResult;
import com.justudy.backend.admin.service.AdminService;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.community.dto.response.CommunityListResult;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.service.MemberService;
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
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    private final MemberService memberService;

    @GetMapping("/total-member")
    public TotalResult getTotalMember() {
        return new TotalResult(adminService.getCountOfMembers());
    }

    @GetMapping("/member")
    public MemberListResult<List<MemberListResponse>> getMembers(@ModelAttribute MemberSearch memberSearch) {
        return adminService.getMembers(memberSearch.validateNull());
    }

    @GetMapping("/member/{memberSequence}")
    public AdminMemberDetail getMemberDetail(@PathVariable Long memberSequence) {
        return adminService.getMemberDetail(memberSequence);
    }

    @DeleteMapping("/member/{memberSequence}")
    public ResponseEntity<Void> banMember(@PathVariable Long memberSequence, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        memberService.banMember(loginSequence, memberSequence);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/week/signup")
    public Long getWeekSignup() {
        return adminService.getWeekSignup();
    }

    @GetMapping("/community")
    public CommunityListResult<CommunityListResponse> getCommunities(@ModelAttribute CommunitySearch communitySearch) {
        return adminService.getCommunities(communitySearch);
    }
}
