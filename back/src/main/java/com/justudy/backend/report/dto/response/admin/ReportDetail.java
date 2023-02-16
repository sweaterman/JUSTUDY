package com.justudy.backend.report.dto.response.admin;

import com.justudy.backend.common.date.DateChanger;
import com.justudy.backend.report.domain.Report;
import lombok.Data;

@Data
public class ReportDetail {

    private Long sequence;

    private String reporterName;

    private Long targetSequence;

    private String type;

    private String content;

    private boolean isFinished;

    private String createdTime;

    public ReportDetail(Report report) {
        this.sequence = report.getSequence();
        this.reporterName = report.getReporter().getNickname();
        this.targetSequence = report.getTargetSequence();
        this.type = ValidateReportType.valid(report);
        this.content = report.getContent();
        this.isFinished = report.isFinished();
        this.createdTime = DateChanger.format(report.getCreatedTime());
    }
}
