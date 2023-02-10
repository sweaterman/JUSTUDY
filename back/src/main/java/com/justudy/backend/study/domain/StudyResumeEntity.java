package com.justudy.backend.study.domain;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Table(name = "study_resume")
@Entity
public class StudyResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_resume_seq")
    private Long sequence;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_seq")
    private StudyEntity study;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private MemberEntity member;
    @Column(name = "content")
    private String content;
    @Column(name = "created_time")
    private LocalDateTime createdTime;
    @Column(name = "modified_time")
    private LocalDateTime modifiedTime;
    @Column(name = "responded_time")
    private LocalDateTime respondedTime;
    @Column(name = "respond")
    @Enumerated(EnumType.STRING)
    private StudyResumeRespond respond;

    public void changeStudy(StudyEntity studyEntity) {
        this.study = studyEntity;
//        if (!studyEntity.getResumes().contains(this))
//            studyEntity.getResumes().add(this);
    }

}
