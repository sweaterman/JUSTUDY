package com.justudy.backend.report.dto;

import lombok.Data;

@Data
public class ReportRequest {

    private Long targetSequence;

    private ReportType type;

    private String content;
}
