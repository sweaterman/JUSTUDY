package com.justudy.backend.study.dto.response;

import com.justudy.backend.study.domain.StudyFrequencyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.text.SimpleDateFormat;

@Getter
@AllArgsConstructor
@Builder
public class StudyFrequencyResponse {
    private String week;
    private String startTime;
    private String endTime;

    public static StudyFrequencyResponse makeBuilder(StudyFrequencyEntity entity) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return StudyFrequencyResponse.builder()
                .week(entity.getWeek().getValue())
                .startTime(simpleDateFormat.format(entity.getStartTime()))
                .endTime(simpleDateFormat.format(entity.getEndTime()))
                .build();
    }
}
