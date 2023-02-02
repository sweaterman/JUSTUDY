package com.justudy.backend.study.dto.response;

import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.study.domain.StudyResumeRespond;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class StudyResumeResponse {
    private Long sequence;
    private Long studySeq;
    private Long memberSeq;
    private String content;
    private LocalDateTime createdTime;

    public static StudyResumeResponse makeBuilder(StudyResumeEntity entity) {
        return StudyResumeResponse.builder()
                .sequence(entity.getSequence())
                .studySeq(entity.getStudy().getSequence())
                .memberSeq(entity.getMember().getSequence())
                .content(entity.getContent())
                .createdTime(entity.getCreatedTime())
                .build();
    }
}
