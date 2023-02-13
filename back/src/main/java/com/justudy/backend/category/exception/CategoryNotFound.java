package com.justudy.backend.category.exception;

import com.justudy.backend.exception.CustomException;

import java.util.Map;

public class CategoryNotFound extends CustomException {

    private static final String MESSAGE = "존재하지 카테고리입니다.";

    public CategoryNotFound() {
        super(MESSAGE);
    }

    public CategoryNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNotFound(String fieldName, String errorMessage) {
        super(MESSAGE);
        addValidation(fieldName, errorMessage);
    }

    @Override
    public Map<String, String> getValidation() {
        return super.getValidation();
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}