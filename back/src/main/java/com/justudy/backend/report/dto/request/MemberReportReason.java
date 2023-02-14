package com.justudy.backend.report.dto.request;

public enum MemberReportReason implements ReasonModel {

    NICKNAME("nickname", "적절하지 않은 닉네임입니다."),
    INTRODUCTION("introduction", "적절하지 않은 자기소개입니다.");

    private String value;

    private String message;

    MemberReportReason(String value, String message) {
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
