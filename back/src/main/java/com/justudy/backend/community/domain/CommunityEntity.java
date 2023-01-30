package com.justudy.backend.community.domain;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.domain.QMemberEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
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
    @JoinColumn(name = "category_seq")
    private Long category_seq;
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

    //== 연관관계 편의메소드 ==//
    public void addMember(MemberEntity member) {
        this.member = member;
        //todo Member와 연관관계 업데이트
    }

    public void changeViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public void changeWeekLoveCount(Integer weekLoveCount) {
        this.weekLoveCount = weekLoveCount;
    }

    public void update(String title, String content, Integer viewCount, LocalDateTime modifiedTime) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.modifiedTime = modifiedTime;
    }
}
