package com.justudy.backend.exception.controller;

import com.justudy.backend.exception.CustomException;
import com.justudy.backend.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customException(CustomException e) {
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return ResponseEntity.status(statusCode).body(body);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.class)
    public ErrorResponse httpClientErrorException(HttpClientErrorException e) {
        ErrorResponse repnose = ErrorResponse.builder()
                .code("401")
                .message("MatterMost 검증에 실패하였습니다.")
                .build();
        return repnose;
    }
}
