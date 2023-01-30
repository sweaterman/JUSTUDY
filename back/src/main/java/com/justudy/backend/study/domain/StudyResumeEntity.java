package com.justudy.backend.study.domain;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Table(name = "study_resume")
@Entity
public class StudyResumeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "study_resume_seq")
    private Long sequence;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_resume_study_seq")
    private StudyEntity study;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_resume_member_seq")
    private MemberEntity member;
    @Column(name = "study_resume_content")
    private String content;
    @Column(name = "study_resume_created_time")
    private LocalDateTime createdTime;
    @Column(name = "study_resume_modified_time")
    private LocalDateTime modifiedTime;
    @Column(name = "study_resume_responded_time")
    private LocalDateTime respondedTime;
    @Column(name = "study_resume_respond")
    @Enumerated(EnumType.STRING)
    private StudyResumeRespond respond;

}
