package com.justudy.backend.community.exception;

import com.justudy.backend.exception.CustomException;

/**
 * status -> 404
 */
public class CommunityLoveAlreadyCreated extends CustomException {

    private static final String MESSAGE = "이미 좋아요를 눌렀습니다.";

    public CommunityLoveAlreadyCreated() {
        super(MESSAGE);
    }

    public CommunityLoveAlreadyCreated(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 409;
    }
}
