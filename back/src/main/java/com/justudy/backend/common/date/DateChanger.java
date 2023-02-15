package com.justudy.backend.common.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateChanger {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(PATTERN));
    }
}
