package com.justudy.backend.report.exception;

import com.justudy.backend.exception.CustomException;

public class ReportNotFound extends CustomException {

    private static String MESSAGE = "해당하는 신고가 없습니다.";

    public ReportNotFound() {
        super(MESSAGE);
    }

    public ReportNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public void addValidation(String fieldName, String errorMessage) {
        super.addValidation(fieldName, errorMessage);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
