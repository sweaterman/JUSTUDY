package com.justudy.backend.category.exception;

import com.justudy.backend.exception.CustomException;

public class CategoryNotFound extends CustomException {

    private static final String MESSAGE = "존재하지 카테고리입니다.";

    public CategoryNotFound() {
        super(MESSAGE);
    }

    public CategoryNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}