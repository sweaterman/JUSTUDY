package com.justudy.backend.member.exception;

import com.justudy.backend.exception.CustomException;

/**
 * status -> 404
 */
public class MemberNotFound extends CustomException {

    private static final String MESSAGE = "존재하지 않는 회원입니다.";

    public MemberNotFound() {
        super(MESSAGE);
    }

    public MemberNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
