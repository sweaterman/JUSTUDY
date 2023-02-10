package com.justudy.backend.study.domain.community;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "study_community")
@Entity
public class StudyCommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_community_seq")
    private Long sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_seq")
    private StudyEntity study;

    @Column(name = "study_community_title")
    private String title;

    @Column(name = "study_community_content")
    private String content;

    @Column(name = "study_community_view_count")
    private Integer viewCount;

    @Column(name = "study_community_love_count")
    private Integer loveCount;

    @Column(name = "study_community_week_love_count")
    private Integer weekLoveCount;

    @Column(name = "study_community_created_time")
    private LocalDateTime createdTime;

    @Column(name = "study_community_modified_time")
    private LocalDateTime modifiedTime;

    @Column(name = "study_community_is_deleted", columnDefinition = "TINYINT(1)")
    private Boolean isDeleted;

    @Column(name = "study_community_is_highlighted", columnDefinition = "TINYINT(1)")
    private Boolean isHighlighted;

    @Builder
    public StudyCommunityEntity(String title, String content,
                                Boolean isHighlighted) {
        this.title = title;
        this.content = content;
        this.isHighlighted = isHighlighted;
        this.viewCount = 0;
        this.loveCount = 0;
        this.weekLoveCount = 0;
        this.createdTime = LocalDateTime.now();
        this.modifiedTime = createdTime;
        this.isDeleted = false;
    }
    public void changeStudy(StudyEntity study) {
        this.study = study;
    }


    public void addViewCount() {
        this.viewCount += 1;
    }

    public void mergeLoveCountWithWeek() {
        this.loveCount += weekLoveCount;
        weekLoveCount = 0;
    }

    public void addWeekLoveCount() {
        this.weekLoveCount += 1;
    }

    public void removeWeekLoveCount() {
        this.weekLoveCount -= 1;
    }

    public void deleteCommunity() {
        this.isDeleted = true;
    }

    public void update(String title, String content, StudyEntity study) {
        this.title = title;
        this.content = content;
        this.study = study;
        this.modifiedTime = LocalDateTime.now();
    }
    public void addMember(MemberEntity member) {
        this.member = member;
    }

    public void addStudy(StudyEntity studyEntity) {
        this.study=studyEntity;
    }
}
