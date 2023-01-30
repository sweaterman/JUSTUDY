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
public class StudyResumeEdit {
    private Long sequence;
    private StudyEntity study;
    private MemberEntity member;
    private String content;
    private LocalDateTime modifiedTime;
    private LocalDateTime respondedTime;
    private StudyResumeRespond respond;

    public StudyResumeEntity toEntity() {
        return StudyResumeEntity
                .builder()
                .sequence(sequence)
                .study(study)
                .member(member)
                .content(content)
                .modifiedTime(LocalDateTime.now())
                .respondedTime(respondedTime)
                .respond(respond)
                .build();
    }
}
