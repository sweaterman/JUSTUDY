package com.justudy.backend.study.domain;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.file.domain.UploadFileEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyMemberEntity> studyMembers = new ArrayList<>();
    @Builder.Default
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study")
    private List<StudyResumeEntity> resumes = new ArrayList<>();
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    private List<StudyCommunityEntity> communities = new ArrayList<>();
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyFrequencyEntity> frequency = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_category_seq")
    private CategoryEntity category;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "studyEntity")
//    @JoinColumn(name = "study_room_seq")
    private StudyRoomEntity studyRoom;
    @Column(name = "study_name")
    private String name;
    @Column(name = "study_leader_seq")
    private Long leaderSeq;
    @Column(name = "study_introduction")
    private String introduction;
    @Column(name = "study_population")
    private Integer population;
    @Column(name = "study_level")
    private String level;
    @Column(name = "study_online_offline")
    private String meeting;
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

    public void changeImage(UploadFileEntity imageFile) {
        this.imageFile = imageFile;
    }

    public void changeLeader(Long leaderSeq) {
        this.leaderSeq = leaderSeq;
    }

    public void addStudyFrequency(StudyFrequencyEntity entity) {
        this.frequency.add(entity);
        if (entity.getStudy() != this) {
            entity.changeStudy(this);
        }
    }

    public void removeStudyFrequency(StudyFrequencyEntity entity) {
        this.frequency.remove(entity);
        entity.changeStudy(null);
    }

    public void addStudyResume(StudyResumeEntity entity) {
        this.resumes.add(entity);
        if (entity.getStudy() != this) {
            entity.changeStudy(this);
        }
    }

    public void removeStudyResume(StudyResumeEntity entity) {
        this.resumes.remove(entity);
        entity.changeStudy(null);
    }

    public void addStudyMember(StudyMemberEntity entity) {
        this.studyMembers.add(entity);
        if (entity.getStudy() != this) {
            entity.changeStudy(this);
        }
    }

    public void removeStudyMember(StudyMemberEntity entity) {
        this.studyMembers.remove(entity);
        entity.changeStudy(null);
    }

    public void update(String name, String introduction, Integer population, String level, String onlineOffline,
                       Boolean isOpen, String github, String notion, String startTime) {
        this.name = name;
        this.introduction = introduction;
        this.population = population;
        this.level = level;
        this.meeting = onlineOffline;
        this.isOpen = isOpen;
        this.github = github;
        this.notion = notion;
        this.startTime = startTime;
        this.modifiedTime = LocalDateTime.now();
    }

}
