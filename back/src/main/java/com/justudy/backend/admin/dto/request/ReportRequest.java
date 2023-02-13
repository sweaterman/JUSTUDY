package com.justudy.backend.admin.dto.request;

import lombok.Data;

@Data
public class ReportRequest {

    private Long targetSequence;

    private ReportType type;

    private String content;
}
