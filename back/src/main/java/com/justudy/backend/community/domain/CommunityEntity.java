package com.justudy.backend.community.domain;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "community")
@Entity
public class CommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "community_seq")
    private Long sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_seq")
    private CategoryEntity category;

    @Column(name = "community_title")
    private String title;

    @Column(name = "community_content")
    private String content;

    @Column(name = "community_view_count")
    private Integer viewCount;

    @Column(name = "community_created_time")
    private LocalDateTime createdTime;

    @Column(name = "community_modified_time")
    private LocalDateTime modifiedTime;

    @Column(name = "community_is_deleted", columnDefinition = "TINYINT(1)")
    private Boolean isDeleted;

    @Column(name = "community_is_highlighted", columnDefinition = "TINYINT(1)")
    private Boolean isHighlighted;

    @Column(name = "community_week_love_count")
    private Integer weekLoveCount;

    @Builder
    public CommunityEntity(String title, String content,
                           Boolean isHighlighted) {
        this.title = title;
        this.content = content;
        this.isHighlighted = isHighlighted;
        this.viewCount = 0;
        this.weekLoveCount = 0;
        this.createdTime = LocalDateTime.now();
        this.modifiedTime = createdTime;
        this.isDeleted = false;
    }

    //== 연관관계 편의메소드 ==//
    public void addMember(MemberEntity member) {
        this.member = member;
        //todo Member와 연관관계 업데이트
    }

    public void changeCategory(CategoryEntity category) {
        this.category = category;
    }

    public void changeWeekLoveCount(Integer weekLoveCount) {
        this.weekLoveCount = weekLoveCount;
    }

    public void addViewCount() {
        this.viewCount += 1;
    }

    public void deleteCommunity() {
        this.isDeleted = true;
    }

    public void update(String title, String content, CategoryEntity category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.modifiedTime = LocalDateTime.now();
    }
}
