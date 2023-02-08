package com.justudy.backend.member.exception;

import com.justudy.backend.exception.CustomException;

public class FollowNotFound extends CustomException {

    private static final String MESSAGE = "존재하지 않는 팔로우입니다.";

    public FollowNotFound() {
        super(MESSAGE);
    }

    public FollowNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
