package com.justudy.backend.study.dto.request;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class StudyFrequencyCreate {
    private Long studySeq;
    private String week;
    private Date startTime;
    private Date endTime;

    public StudyFrequencyEntity toEntity(StudyEntity study) {
        return StudyFrequencyEntity
                .builder()
                .study(study)
                .week(week)
                .startTime(startTime)
                .endTime(endTime)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
