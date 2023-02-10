package com.justudy.backend.community.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "community_bookmark")
@Entity
public class CommunityBookmarkEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_seq")
    private Long sequence;

    @Column(name = "member_seq")
    private Long memberSequence;

    @Column(name = "community_seq")
    private Long communitySequence;

    @Column(name = "bookmark_created_time")
    private LocalDateTime createdTime;

    @Builder
    public CommunityBookmarkEntity(Long memberSequence, Long communitySequence) {
        this.memberSequence = memberSequence;
        this.communitySequence = communitySequence;
        this.createdTime = LocalDateTime.now();
    }
}
