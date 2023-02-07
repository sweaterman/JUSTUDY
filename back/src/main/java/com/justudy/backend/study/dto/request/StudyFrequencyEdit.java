package com.justudy.backend.study.dto.request;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyFrequencyWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class StudyFrequencyEdit {
    private Long sequence;
    private Long studySeq;
    private String week;
    private Date startTime;
    private Date endTime;
    private LocalDateTime modifiedTime;

    public StudyFrequencyEntity toEntity(StudyEntity study) {
        return StudyFrequencyEntity
                .builder()
                .sequence(sequence)
                .study(study)
                .week(StudyFrequencyWeek.valueOf(week))
                .startTime(startTime)
                .endTime(endTime)
                .modifiedTime(LocalDateTime.now())
                .build();
    }
}
