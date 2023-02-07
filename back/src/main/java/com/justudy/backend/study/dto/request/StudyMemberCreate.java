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
public class StudyMemberCreate {
    private Long memberSeq;
    private Long studySeq;

    public StudyMemberEntity toEntity(MemberEntity memberEntity, StudyEntity studyEntity) {
        return StudyMemberEntity.builder()
                .member(memberEntity)
                .study(studyEntity)
                .alarm(true)
                .badge(0)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
