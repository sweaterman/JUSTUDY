package com.justudy.backend.report.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class CommunityReportDetail {

    private String writer;

    private String title;

    private Map<String, String> reason;

    public CommunityReportDetail(String writer,
                                 String title,
                                 Map<String, String> reason) {
        this.writer = writer;
        this.title = title;
        this.reason = reason;
    }
}
