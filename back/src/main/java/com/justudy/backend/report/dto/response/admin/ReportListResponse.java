package com.justudy.backend.report.dto.response.admin;

import lombok.Data;

@Data
public class ReportListResponse {

    private Long reportSequence;

    private String category;

    private Long targetSequence;

    private String reportContent;

    public ReportListResponse(Long reportSequence, String category,
                              Long targetSequence, String reportContent) {
        this.reportSequence = reportSequence;
        this.category = category;
        this.targetSequence = targetSequence;
        this.reportContent = reportContent;
    }
}
