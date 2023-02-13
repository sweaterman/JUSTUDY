package com.justudy.backend.study.dto.request;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyFrequencyWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class StudyFrequencyCreate {
    private Long studySeq;
    private String week;
    private String startTime;
    private String endTime;

    public StudyFrequencyEntity toEntity(StudyEntity study) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return StudyFrequencyEntity
                .builder()
                .study(study)
                .week(StudyFrequencyWeek.valueOf(week))
                .startTime(simpleDateFormat.parse(startTime))
                .endTime(simpleDateFormat.parse(endTime))
                .createdTime(LocalDateTime.now())
                .build();
    }
}
