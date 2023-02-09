package com.justudy.backend.timer.exception;

import com.justudy.backend.exception.CustomException;
import org.springframework.http.HttpStatus;


public class NicknameNotFound extends CustomException {

    private static final String MESSAGE = "해당 유저는 존재하지 않습니다.";

    public NicknameNotFound() {
        super(MESSAGE);
    }

    public NicknameNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
