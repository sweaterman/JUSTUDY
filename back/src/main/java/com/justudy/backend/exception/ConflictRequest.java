package com.justudy.backend.exception;

import com.justudy.backend.exception.CustomException;

/**
 * status -> 409
 */
public class ConflictRequest extends CustomException {

    private static final String MESSAGE = "중복된 값이 존재합니다.";

    public ConflictRequest() {
        super(MESSAGE);
    }

    public ConflictRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictRequest(String fieldName, String errorMessage) {
        super(MESSAGE);
        addValidation(fieldName, errorMessage);
    }

    @Override
    public int getStatusCode() {
        return 409;
    }
}
