package com.justudy.backend.timer.exception;

import com.justudy.backend.exception.CustomException;
import org.springframework.http.HttpStatus;


public class StudyNotFound extends CustomException {

    private static final String MESSAGE = "해당 스터디는 존재하지 않습니다.";

    public StudyNotFound() {
        super(MESSAGE);
    }

    public StudyNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
