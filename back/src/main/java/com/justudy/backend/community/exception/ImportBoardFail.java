package com.justudy.backend.community.exception;

import com.justudy.backend.exception.CustomException;

public class ImportBoardFail extends CustomException {

    private static final String MESSAGE = "데이터를 가져오는데 실패했습니다.";

    public ImportBoardFail() {
        super(MESSAGE);
    }

    public ImportBoardFail(String message) {
        super(message);
    }

    public ImportBoardFail(String fieldName, String errorMessage) {
        super(MESSAGE);
        addValidation(fieldName, errorMessage);
    }

    @Override
    public void addValidation(String fieldName, String errorMessage) {
        super.addValidation(fieldName, errorMessage);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
