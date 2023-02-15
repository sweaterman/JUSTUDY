package com.justudy.backend.report.exception;

import com.justudy.backend.exception.CustomException;

public class ReportReasonNotFound extends CustomException {

    private static final String MESSAGE = "해당하는 신고 이유가 없습니다.";

    public ReportReasonNotFound() {
        super(MESSAGE);
    }

    public ReportReasonNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

    @Override
    public void addValidation(String fieldName, String errorMessage) {
        super.addValidation(fieldName, errorMessage);
    }
}
