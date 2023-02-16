package com.justudy.backend.report.dto.request;

public enum CommentReportReason implements ReasonModel {

    ABUSE("abuse", "욕설/혐오/차별적 표현입니다."),
    PRIVACY("privacy", "개인정보 노출 게시물입니다."),
    SPAM("spam", "스팸홍보/도배글입니다.");

    private String value;

    private String message;

    CommentReportReason(String value, String message) {
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
