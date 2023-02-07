package com.justudy.backend.study.dto.request;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyFrequencyWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class StudyFrequencyCreate {
    private Long studySeq;
    private Long week;
    private Date startTime;
    private Date endTime;

    public StudyFrequencyEntity toEntity(StudyEntity study) {
        return StudyFrequencyEntity
                .builder()
                .study(study)
                .week(StudyFrequencyWeek.valueOf(StudyFrequencyWeek.getIndex(week.intValue())))
                .startTime(startTime)
                .endTime(endTime)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
