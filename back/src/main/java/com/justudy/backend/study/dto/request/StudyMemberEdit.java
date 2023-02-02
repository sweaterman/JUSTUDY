package com.justudy.backend.study.dto.request;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class StudyMemberEdit {
    private Long sequence;
    private String memberSeq;
    private String studySeq;
    private Boolean alarm;
    private Integer badge;

    public StudyMemberEntity toEntity(MemberEntity memberEntity, StudyEntity studyEntity) {
        return StudyMemberEntity
                .builder()
                .sequence(sequence)
                .member(memberEntity)
                .study(studyEntity)
                .alarm(alarm)
                .badge(badge)
                .modifiedTime(LocalDateTime.now())
                .build();
    }
}
