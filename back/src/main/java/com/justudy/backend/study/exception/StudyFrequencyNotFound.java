package com.justudy.backend.study.exception;

import com.justudy.backend.exception.CustomException;

/**
 * status -> 404
 */
public class StudyFrequencyNotFound extends CustomException {

    private static final String MESSAGE = "존재하지 않는 스터디 활동주기 입니다.";

    public StudyFrequencyNotFound() {
        super(MESSAGE);
    }

    public StudyFrequencyNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
