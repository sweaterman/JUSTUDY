package com.justudy.backend.report.repository;

import com.justudy.backend.report.domain.Report;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReportRepositoryCustom {

    List<Report> getReportList(Pageable pageable);

    Long getCountOfReportList();

    Optional<Report> findReportBySequence(Long sequence);

    Optional<Report> findReportByTargetSequence(Long targetSequence);

    Long getCountOfMemberReport();

    Long getCountOfCommunityReport();

    Long getCountOfCommentReport();

    Long getCountOfStudyReport();
}
