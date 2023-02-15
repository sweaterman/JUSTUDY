package com.justudy.backend.report.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class CommentReportDetail {

    private String writer;

    private String content;

    private Map<String, String> reason;

    public CommentReportDetail(String writer, String content, Map<String, String> reason) {
        this.writer = writer;
        this.content = content;
        this.reason = reason;
    }
}
