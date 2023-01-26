package com.justudy.backend.community.exception;

import com.justudy.backend.exception.CustomException;

/**
 * status -> 404
 */
public class CommunityLoveNotFound extends CustomException {

    private static final String MESSAGE = "잘못된 요청입니다.";

    public CommunityLoveNotFound() {
        super(MESSAGE);
    }

    public CommunityLoveNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
