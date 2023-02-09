package com.justudy.backend.community.exception;

import com.justudy.backend.exception.CustomException;

public class BookmarkNotFound extends CustomException {

    private static final String MESSAGE = "요청한 북마크가 없습니다.";

    public BookmarkNotFound() {
        super(MESSAGE);
    }

    public BookmarkNotFound(String message, Throwable cause) {
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
