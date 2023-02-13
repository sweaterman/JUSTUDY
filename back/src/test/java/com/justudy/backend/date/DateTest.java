package com.justudy.backend.date;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTest {

    @Test
    void getDateOfWeek() {

        LocalDateTime now = LocalDateTime.now();
        now = now.plusDays(3);
        LocalDate localDate = now.toLocalDate();
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        LocalDateTime startOfWeek = now.with(DayOfWeek.MONDAY);
        LocalDateTime endOfWeek = now.with(DayOfWeek.SUNDAY);

        LocalDate start = localDate.with(DayOfWeek.MONDAY);
        LocalDate end = localDate.with(DayOfWeek.SUNDAY);

        LocalDateTime transferStart = start.atStartOfDay();
        LocalDateTime transferEnd = end.atTime(LocalTime.MAX);

        System.out.println(transferStart);
        System.out.println(transferEnd);
    }
}
