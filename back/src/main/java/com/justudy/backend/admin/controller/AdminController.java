package com.justudy.backend.admin.controller;

import com.justudy.backend.admin.dto.request.MemberSearch;
import com.justudy.backend.admin.dto.response.MemberListResponse;
import com.justudy.backend.admin.dto.response.MemberListResult;
import com.justudy.backend.admin.dto.response.TotalResult;
import com.justudy.backend.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/total-member")
    public TotalResult getTotalMember() {
        return new TotalResult(adminService.getCountOfMembers());
    }

    @GetMapping("/member")
    public MemberListResult<MemberListResponse> getMembers(@ModelAttribute MemberSearch memberSearch) {
        return adminService.getMembers(memberSearch);
    }
}
