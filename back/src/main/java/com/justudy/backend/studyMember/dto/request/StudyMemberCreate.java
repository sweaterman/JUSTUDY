package com.justudy.backend.studyMember.dto.request;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class StudyMemberCreate {
    private MemberEntity member;
    private StudyEntity study;

    public StudyMemberEntity toEntity() {
        return StudyMemberEntity.builder()
                .member(member)
                .study(study)
                .alarm(true)
                .king(0)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
