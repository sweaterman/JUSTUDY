package com.justudy.backend.study.domain;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Table(name = "study")
@Entity
public class StudyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "study_seq")
    private Long sequence;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_member_seq")
    private List<StudyMemberEntity> studyMembers;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_resume_seq")
    private List<StudyResumeEntity> resumes;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_community_seq")
    private List<StudyCommunityEntity> communities;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_frequency_seq")
    private List<StudyFrequencyEntity> frequencies;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_category_seq")
    private CategoryEntity category;
    @Column(name = "study_name")
    private String name;
    @Column(name = "study_leader_seq")
    private Long leaderSeq;
    @Column(name = "study_introduction")
    private String introduction;
    @Column(name = "study_personnel")
    private Integer personnel;
    @Column(name = "study_level")
    private String level;
    @Column(name = "study_online_offline")
    private String onlineOffline;
    @Column(name = "study_is_open", columnDefinition = "TINYINT(1)")
    private Boolean isOpen;
    @Column(name = "study_github")
    private String github;
    @Column(name = "study_notion")
    private String notion;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "upload_file_seq")
    private UploadFileEntity imageFile;
    @Column(name = "study_created_time")
    private LocalDateTime createdTime;
    @Column(name = "study_modified_time")
    private LocalDateTime modifiedTime;
    @Column(name = "study_start_time")
    private String startTime;
    //스터디방

    public void changeImage(UploadFileEntity imageFile) {
        this.imageFile = imageFile;
    }
    //todo change frequencies
//    public void changeFrequencies(StudyFrequencyEntity frequencies) {
//        this.frequencies = frequencies;
//    }

    public void update(String name, Long leaderSeq, String introduction, Integer personnel, String level, String onlineOffline,
                       Boolean isOpen, String github, String notion, String startTime) {
        this.name = name;
        this.leaderSeq = leaderSeq;
        this.introduction = introduction;
        this.personnel = personnel;
        this.level = level;
        this.onlineOffline = onlineOffline;
        this.isOpen = isOpen;
        this.github = github;
        this.notion = notion;
        this.startTime = startTime;
        this.modifiedTime = LocalDateTime.now();
    }
}
