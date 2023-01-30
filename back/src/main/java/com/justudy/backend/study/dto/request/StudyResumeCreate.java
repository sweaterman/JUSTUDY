package com.justudy.backend.study.dto.request;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.study.domain.StudyResumeRespond;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class StudyResumeCreate {
    private StudyEntity study;
    private MemberEntity member;
    private String content;

    public StudyResumeEntity toEntity() {
        return StudyResumeEntity
                .builder()
                .study(study)
                .member(member)
                .content(content)
                .createdTime(LocalDateTime.now())
                .respond(StudyResumeRespond.WAITING)
                .build();
    }
}
