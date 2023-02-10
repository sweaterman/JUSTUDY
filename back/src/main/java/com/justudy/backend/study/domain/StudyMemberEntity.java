package com.justudy.backend.study.domain;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Table(name = "study_member")
@Entity
public class StudyMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_member_seq")
    private Long sequence;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private MemberEntity member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_seq")
    private StudyEntity study;
    @Column(name = "study_member_alarm", columnDefinition = "TINYINT(1)")
    private Boolean alarm;
    @Column(name = "study_member_badge")
    private Integer badge;
    @Column(name = "study_member_created_time")
    private LocalDateTime createdTime;
    @Column(name = "study_member_modified_time")
    private LocalDateTime modifiedTime;

    public void changeBadge(Integer badge) {
        this.badge = badge;
    }

    public void changeStudy(StudyEntity studyEntity) {
        this.study = studyEntity;
//        if (!studyEntity.getStudyMembers().contains(this))
//            studyEntity.getStudyMembers().add(this);
    }
}
