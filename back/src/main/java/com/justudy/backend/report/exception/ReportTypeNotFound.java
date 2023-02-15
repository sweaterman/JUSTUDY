package com.justudy.backend.report.exception;

import com.justudy.backend.exception.CustomException;

public class ReportTypeNotFound extends CustomException {

    private static final String MESSAGE = "신고타입이 옳지 않습니다.";

    public ReportTypeNotFound() {
        super(MESSAGE);
    }

    public ReportTypeNotFound(String message, Throwable cause) {
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
