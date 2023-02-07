package com.justudy.backend.community.exception;

import com.justudy.backend.exception.CustomException;

/**
 * status -> 404
 */
public class LoveNotFound extends CustomException {

    private static final String MESSAGE = "요청한 '좋아요'가 없습니다.";

    public LoveNotFound() {
        super(MESSAGE);
    }

    public LoveNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
