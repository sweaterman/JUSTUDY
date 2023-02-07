package com.justudy.backend.community.exception;

import com.justudy.backend.exception.CustomException;

public class SearchTypeNotFound extends CustomException {

    private static final String MESSAGE = "잘못된 검색조건입니다.";

    public SearchTypeNotFound() {
        super(MESSAGE);
    }

    public SearchTypeNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchTypeNotFound(String fieldName, String errorMessage) {
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
