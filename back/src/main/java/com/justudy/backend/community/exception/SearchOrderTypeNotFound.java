package com.justudy.backend.community.exception;

import com.justudy.backend.exception.CustomException;

public class SearchOrderTypeNotFound extends CustomException {

    private static final String MESSAGE = "잘못된 정렬조건입니다.";

    public SearchOrderTypeNotFound() {
        super(MESSAGE);
    }

    public SearchOrderTypeNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchOrderTypeNotFound(String fieldName, String errorMessage) {
        super(MESSAGE);
        addValidation(fieldName, errorMessage);
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
