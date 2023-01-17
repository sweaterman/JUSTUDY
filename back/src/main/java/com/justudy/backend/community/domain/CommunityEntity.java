package com.justudy.backend.community.domain;

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
    @Column(name = "community_created_time")
    private LocalDateTime createdTime;
    @Column(name = "community_modified_time")
    private LocalDateTime modifiedTime;
    @Column(name = "community_is_deleted",columnDefinition = "TINYINT(1)")
    private Boolean isDeleted;
    @Column(name = "community_is_highlighted",columnDefinition = "TINYINT(1)")
    private Boolean isHighlighted;


    public void changeTitle(String title){
        this.title=title;
    }
    public void changeContent(String content){
        this.content=content;
    }
    public void changeViewCount(Integer viewCount){
        this.viewCount=viewCount;
    }
    public void changeModifiedTime(LocalDateTime modifiedTime){
        this.modifiedTime=modifiedTime;
    }

    public void update(String title, String content, Integer viewCount, LocalDateTime modifiedTime) {
        this.title=title;
        this.content=content;
        this.viewCount=viewCount;
        this.modifiedTime=modifiedTime;
    }
}
