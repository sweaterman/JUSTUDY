package com.justudy.backend.study.dto.response;

import com.justudy.backend.study.domain.StudyFrequencyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class StudyFrequencyResponse {
    private Long sequence;
    private Long studySeq;
    private String week;
    private Date startTime;
    private Date endTime;

    public static StudyFrequencyResponse makeBuilder(StudyFrequencyEntity entity) {
        return StudyFrequencyResponse.builder()
                .sequence(entity.getSequence())
                .studySeq(entity.getStudy().getSequence())
                .week(entity.getWeek())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .build();
    }
}
