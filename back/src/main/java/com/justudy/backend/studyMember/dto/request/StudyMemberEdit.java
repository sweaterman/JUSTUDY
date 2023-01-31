package com.justudy.backend.studyMember.dto.request;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class StudyMemberEdit {
    private Long sequence;
    private MemberEntity member;
    private StudyEntity study;
    private Boolean alarm;
    private Integer king;

    public StudyMemberEntity toEntity(){
        return StudyMemberEntity
                .builder()
                .sequence(sequence)
                .member(member)
                .study(study)
                .alarm(alarm)
                .king(king)
                .createdTime(null)
                .build();
    }
}
