package com.justudy.backend.report.domain;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("study")
@Entity
public class StudyReport extends Report {

    @ManyToOne
    @JoinColumn(name = "study_seq")
    private StudyEntity targetStudy;

    public StudyReport(MemberEntity reporter, String content,
                       StudyEntity targetStudy) {
        super(reporter, targetStudy.getSequence(), content);
        this.targetStudy = targetStudy;
    }
}
