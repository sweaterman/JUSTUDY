package com.justudy.backend.study.exception;

import com.justudy.backend.exception.CustomException;

/**
 * status -> 404
 */
public class StudyResumeNotFound extends CustomException {

    private static final String MESSAGE = "존재하지 않는 지원서 입니다.";

    public StudyResumeNotFound() {
        super(MESSAGE);
    }

    public StudyResumeNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
