package com.justudy.backend.timer.exception;

import com.justudy.backend.exception.CustomException;
import org.springframework.http.HttpStatus;


public class YesterdayNoData extends CustomException {

    private static final String MESSAGE = "어제 데이터가 존재하지 않습니다.";

    public YesterdayNoData() {
        super(MESSAGE);
    }

    public YesterdayNoData(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
