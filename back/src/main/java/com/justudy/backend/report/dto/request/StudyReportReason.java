package com.justudy.backend.report.dto.request;

public enum StudyReportReason implements ReasonModel {

    NAME("nickname", "적절하지 않은 스터디 이름입니다."),
    INTRODUCTION("introduction", "적절하지 않은 스터디 소개입니다.");

    private String value;

    private String message;

    StudyReportReason(String value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
