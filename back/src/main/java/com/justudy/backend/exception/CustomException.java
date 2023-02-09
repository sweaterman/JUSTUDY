package com.justudy.backend.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class CustomException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String errorMessage) {
        validation.put(fieldName, errorMessage);
    }
}
