package com.justudy.backend.community.exception;

import com.justudy.backend.exception.CustomException;

/**
 * status -> 404
 */
public class CommentNotFound extends CustomException {

    private static final String MESSAGE = "존재하지 않는 댓글 입니다.";

    public CommentNotFound() {
        super(MESSAGE);
    }

    public CommentNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
