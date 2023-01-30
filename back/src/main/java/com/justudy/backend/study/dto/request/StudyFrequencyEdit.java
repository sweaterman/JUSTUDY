package com.justudy.backend.study.dto.request;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StudyFrequencyEdit {
    private Long sequence;
    private StudyEntity study;
    private String week;
    private Date startTime;
    private Date endTime;
    private LocalDateTime modifiedTime;

    public StudyFrequencyEntity toEntity() {
        return StudyFrequencyEntity
                .builder()
                .sequence(sequence)
                .study(study)
                .week(week)
                .startTime(startTime)
                .endTime(endTime)
                .modifiedTime(LocalDateTime.now())
                .build();
    }
}
