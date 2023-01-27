package com.justudy.backend.file.exception;

import com.justudy.backend.exception.CustomException;

public class UploadFileNotFound extends CustomException {

    private static final String MESSAGE = "해당하는 파일이 없습니다.";

    public UploadFileNotFound() {
        super(MESSAGE);
    }

    public UploadFileNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
