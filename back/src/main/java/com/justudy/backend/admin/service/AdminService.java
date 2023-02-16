package com.justudy.backend.admin.service;

import com.justudy.backend.admin.dto.request.MemberSearch;
import com.justudy.backend.admin.dto.response.AdminMemberDetail;
import com.justudy.backend.admin.dto.response.CountReport;
import com.justudy.backend.admin.dto.response.MemberListResponse;
import com.justudy.backend.admin.dto.response.MemberListResult;
import com.justudy.backend.admin.repository.AdminRepository;
import com.justudy.backend.common.validate.Validation;
import com.justudy.backend.community.domain.CommunityCommentEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.response.CommunityDetailResponse;
import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.community.dto.response.CommunityListResult;
import com.justudy.backend.community.exception.CommentNotFound;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.repository.CommunityCommentRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.domain.MemberRole;
import com.justudy.backend.member.exception.MemberNotFound;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.report.domain.Report;
import com.justudy.backend.report.exception.ReportNotFound;
import com.justudy.backend.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    private final MemberRepository memberRepository;

    private final CommunityRepository communityRepository;

    private final CommunityCommentRepository commentRepository;

    private final ReportRepository reportRepository;


    public Long getCountOfMembers() {
        return adminRepository.getCountOfMembers();
    }

    public MemberListResult<List<MemberListResponse>> getMembers(MemberSearch memberSearch) {
        List<MemberListResponse> membersBySearch = adminRepository.getMembers(memberSearch).stream()
                .map(MemberListResponse::toResponse)
                .collect(Collectors.toList());
        Long totalCount = adminRepository.getCountMemberBySearch(memberSearch);

        return new MemberListResult(membersBySearch, totalCount);
    }

    public AdminMemberDetail getMemberDetail(Long memberSequence) {
        MemberEntity findMember = memberRepository.findById(memberSequence)
                .orElseThrow(MemberNotFound::new);
        return new AdminMemberDetail(findMember);
    }

    @Transactional
    public Long banMember(Long loginSequence, Long memberSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);
        Validation.validateUserRole(findMember, MemberRole.ADMIN);

        MemberEntity targetMember = memberRepository.findById(memberSequence)
                .orElseThrow(() -> new MemberNotFound());
        targetMember.banMember();
        return targetMember.getSequence();
    }

    public Long banMemberByReport(Long loginSequence, Long memberSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);
        Validation.validateUserRole(findMember, MemberRole.ADMIN);

        MemberEntity targetMember = memberRepository.findById(memberSequence)
                .orElseThrow(() -> new MemberNotFound());
        targetMember.banMember();
        acceptReport(memberSequence);
        return targetMember.getSequence();
    }

    public Long getWeekSignup() {
        LocalDateTime startDateTime = getStartDateTime();
        LocalDateTime endDateTime = getEndDateTime();
        log.info("startDate = {}", startDateTime);
        log.info("endDate = {}", endDateTime);

        return adminRepository.countSignupByTime(startDateTime, endDateTime);
    }

    public CommunityListResult getCommunities(CommunitySearch communitySearch) {
        List<CommunityListResponse> communityResponses = adminRepository.getCommunities(communitySearch).stream()
                .map(CommunityListResponse::new)
                .collect(Collectors.toList());
        Long totalCount = adminRepository.getCountCommunitiesBySearch(communitySearch);
        return new CommunityListResult<>(communityResponses, totalCount);
    }

    public CommunityDetailResponse getCommunityDetailByAdmin(Long communitySequence) {
        CommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        return CommunityDetailResponse.makeBuilder(community);
    }


    @Transactional
    public Long deleteCommunity(Long loginSequence, Long communitySequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);

        Validation.validateUserRole(findMember, MemberRole.ADMIN);
        CommunityEntity findCommunity = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        findCommunity.deleteCommunity();
        return findCommunity.getSequence();
    }

    @Transactional
    public Long deleteCommunityByReport(Long loginSequence, Long communitySequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);

        Validation.validateUserRole(findMember, MemberRole.ADMIN);
        CommunityEntity findCommunity = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        findCommunity.deleteCommunity();
        acceptReport(communitySequence);
        return findCommunity.getSequence();
    }

    public Long geCountOfCommunityByWeek() {
        LocalDateTime startDateTime = getStartDateTime();
        LocalDateTime endDateTime = getEndDateTime();

        return adminRepository.countCommunityByTime(startDateTime, endDateTime);
    }

    @Transactional
    public Long deleteComment(Long loginSequence, Long commentSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);

        Validation.validateUserRole(findMember, MemberRole.ADMIN);
        CommunityCommentEntity findComment = commentRepository.findById(commentSequence)
                .orElseThrow(CommentNotFound::new);
        findComment.changeIsDeleted(true);
        return findComment.getSequence();
    }

    @Transactional
    public Long deleteCommentByReport(Long loginSequence, Long commentSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);

        Validation.validateUserRole(findMember, MemberRole.ADMIN);
        CommunityCommentEntity findComment = commentRepository.findById(commentSequence)
                .orElseThrow(CommentNotFound::new);
        findComment.changeIsDeleted(true);
        acceptReport(commentSequence);
        return findComment.getSequence();
    }

    public CountReport getCountsOfReport() {
        Long member = reportRepository.getCountOfMemberReport();
        Long community = reportRepository.getCountOfCommunityReport();
        Long comment = reportRepository.getCountOfCommentReport();
        Long study = reportRepository.getCountOfStudyReport();

        return new CountReport(member, community, comment, study);
    }

    private Long acceptReport(Long targetSequence) {
        Report report = reportRepository.findReportByTargetSequence(targetSequence)
                .orElseThrow(ReportNotFound::new);
        return report.acceptReport();
    }

    private LocalDateTime getStartDateTime() {
        LocalDate now = LocalDate.now();
        return now.with(DayOfWeek.MONDAY).atStartOfDay();
    }

    private LocalDateTime getEndDateTime() {
        LocalDate now = LocalDate.now();
        return now.with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX);
    }

    public boolean validateAdmin(Long loginSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);
        Validation.validateUserRole(findMember, MemberRole.ADMIN);
        return true;
    }
}
