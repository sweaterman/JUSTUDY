package com.justudy.backend.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.dialect.Ingres9Dialect;

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
    @JoinColumn(name = "member_seq")
    private Long member_seq;
    @JoinColumn(name = "category_seq")
    private Long category_seq;

    @Column(name = "community_title")
    private String title;
    @Column(name = "community_content")
    private String content;

    @Column(name = "community_view_count")
    private Integer viewCount;

    @Column(name = "community_book_mark")
    private Boolean bookmark;
    @Column(name = "community_created_time")
    private LocalDateTime createdTime;
    @Column(name = "community_modified_time")
    private LocalDateTime modifiedTime;
    @Column(name = "community_love")
    private Integer love;
    @Column(name = "community_is_deleted",columnDefinition = "TINYINT(1)")
    private Boolean isDeleted;

    @Builder

    public CommunityEntity(Long sequence, Long member_seq, Long category_seq, String title, String content, Integer viewCount, Boolean bookmark, LocalDateTime createdTime, LocalDateTime modifiedTime, Integer love, Boolean isDeleted) {
        this.sequence = sequence;
        this.member_seq = member_seq;
        this.category_seq = category_seq;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.bookmark = bookmark;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
        this.love = love;
        this.isDeleted = isDeleted;
    }

    public void changeTitle(String title){
        this.title=title;
    }
    public void changeContent(String content){
        this.content=content;
    }
    public void changeViewCount(Integer viewCount){
        this.viewCount=viewCount;
    }
    public void changeBookmark(Boolean bookmark){
        this.bookmark=bookmark;
    }
    public void changeModifiedTime(LocalDateTime modifiedTime){
        this.modifiedTime=modifiedTime;
    }

    public void update(String title, String content, Integer viewCount, Boolean bookmark, LocalDateTime modifiedTime) {

    }
}
