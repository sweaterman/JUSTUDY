package com.justudy.backend.report.repository;

import com.justudy.backend.report.domain.Report;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportRepositoryCustom {

    List<Report> getReportList(Pageable pageable);

    Long getCountOfReportList();
}
