package com.justudy.backend.report.service;

import com.justudy.backend.community.domain.CommunityCommentEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.exception.CommentNotFound;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.repository.CommunityCommentRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.exception.MemberNotFound;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.report.domain.*;
import com.justudy.backend.report.dto.request.ReportRequest;
import com.justudy.backend.report.dto.response.admin.ReportDetail;
import com.justudy.backend.report.dto.response.admin.ReportListResponse;
import com.justudy.backend.report.dto.response.admin.ReportListResult;
import com.justudy.backend.report.exception.ReportNotFound;
import com.justudy.backend.report.repository.ReportRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    private final MemberRepository memberRepository;

    private final CommunityRepository communityRepository;

    private final CommunityCommentRepository commentRepository;

    private final StudyRepository studyRepository;

    @Transactional
    public Long saveReport(Long loginSequence, Long targetSequence, ReportRequest request) {
        MemberEntity reporter = memberRepository.findById(loginSequence)
                .orElseThrow(MemberNotFound::new);
        Report report = createReport(targetSequence, reporter, request);
        return reportRepository.save(report).getSequence();
    }

    public ReportListResult getReportList(Pageable pageable) {
        List<ReportListResponse> reports = reportRepository.getReportList(pageable).stream()
                .map(ReportListResponse::new)
                .collect(Collectors.toList());
        Long totalCount = reportRepository.getCountOfReportList();
        return new ReportListResult<>(reports, totalCount);
    }

    public ReportDetail getReportDetail(Long reportSequence) {
        Report report = reportRepository.findReportBySequence(reportSequence)
                .orElseThrow(ReportNotFound::new);

        return new ReportDetail(report);
    }



    private Report createReport(Long targetSequence, MemberEntity reporter, ReportRequest request) {
        if (request.getType().getValue().equalsIgnoreCase("member")) {
            MemberEntity member = memberRepository.findBySequenceWithJoin(targetSequence)
                    .orElseThrow(MemberNotFound::new);
            return new MemberReport(reporter, request.getReason().getMessage(), member);
        }
        if (request.getType().getValue().equalsIgnoreCase("community")) {
            CommunityEntity community = communityRepository.findBySequence(targetSequence)
                    .orElseThrow(CommunityNotFound::new);
            return new CommunityReport(reporter, request.getReason().getMessage(), community);
        }
        if (request.getType().getValue().equalsIgnoreCase("comment")) {
            CommunityCommentEntity comment = commentRepository.findById(targetSequence)
                    .orElseThrow(CommentNotFound::new);
            return new CommentReport(reporter, request.getReason().getMessage(), comment);
        }
        if (request.getType().getValue().equalsIgnoreCase("study")) {
            StudyEntity study = studyRepository.findById(targetSequence)
                    .orElseThrow(StudyNotFound::new);
            return new StudyReport(reporter, request.getReason().getMessage(), study);
        }
        return null;
    }
}
