package com.justudy.backend.member.exception;

import com.justudy.backend.exception.CustomException;

public class ForbiddenRequest extends CustomException {

    private static final String MESSAGE = "접근 권한이 없습니다.";

    public ForbiddenRequest() {
        super(MESSAGE);
    }

    public ForbiddenRequest(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 403;
    }
}
