package com.justudy.backend.report.dto.response.admin;

import com.justudy.backend.report.domain.*;
import lombok.Data;

@Data
public class ReportListResponse {

    private Long reportSequence;

    private String type;

    private Long targetSequence;

    private String reportContent;

    public ReportListResponse(Report report) {
        this.reportSequence = report.getSequence();
        this.targetSequence = report.getTargetSequence();
        this.reportContent = report.getContent();
        this.type = validateCategory(report);
    }

    private String validateCategory(Report report) {
        if (report instanceof MemberReport) {
            return "member";
        }
        if (report instanceof CommunityReport) {
            return "community";
        }
        if (report instanceof CommentReport) {
            return "comment";
        }
        if (report instanceof StudyReport) {
            return "study";
        }
        return null;
    }
}
