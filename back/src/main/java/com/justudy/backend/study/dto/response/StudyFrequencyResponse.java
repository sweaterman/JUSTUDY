package com.justudy.backend.study.dto.response;

import com.justudy.backend.study.domain.StudyFrequencyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class StudyFrequencyResponse {
    //    private Long sequence;
//    private Long studySeq;
    private String week;
    private String startTime;
    private String endTime;

    public static StudyFrequencyResponse makeBuilder(StudyFrequencyEntity entity) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return StudyFrequencyResponse.builder()
//                .sequence(entity.getSequence())
//                .studySeq(entity.getStudy().getSequence())
                .week(entity.getWeek().getValue())
                .startTime(simpleDateFormat.format(entity.getStartTime()))
                .endTime(simpleDateFormat.format(entity.getEndTime()))
                .build();
    }
}
