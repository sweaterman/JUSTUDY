package com.justudy.backend.study.dto.response;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.study.domain.StudyResumeRespond;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
public class StudyFrequencyResponse {
    private Long sequence;
    private StudyEntity study;
    private String week;
    private Date startTime;
    private Date endTime;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public static StudyFrequencyResponse makeBuilder(StudyFrequencyEntity entity) {
        return StudyFrequencyResponse.builder()
                .sequence(entity.getSequence())
                .study(entity.getStudy())
                .week(entity.getWeek())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .build();
    }
}
