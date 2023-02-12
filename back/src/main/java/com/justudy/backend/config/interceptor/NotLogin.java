package com.justudy.backend.config.interceptor;

import com.justudy.backend.exception.CustomException;

public class NotLogin extends CustomException {

    final static String MESSAGE = "로그인되지 않았습니다.";

    public NotLogin() {
        super(MESSAGE);
    }

    public NotLogin(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public void addValidation(String fieldName, String errorMessage) {
        super.addValidation(fieldName, errorMessage);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
