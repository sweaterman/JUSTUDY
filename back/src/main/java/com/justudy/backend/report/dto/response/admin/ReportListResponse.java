package com.justudy.backend.report.dto.response.admin;

import com.justudy.backend.report.domain.*;
import lombok.Data;

@Data
public class ReportListResponse {

    private Long reportSequence;

    private String type;

    private Long targetSequence;

    private String content;

    public ReportListResponse(Report report) {
        this.reportSequence = report.getSequence();
        this.targetSequence = report.getTargetSequence();
        this.content = report.getContent();
        this.type = ValidateReportType.valid(report);
    }
}
