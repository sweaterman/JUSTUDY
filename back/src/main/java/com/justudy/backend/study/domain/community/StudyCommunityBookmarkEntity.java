package com.justudy.backend.study.domain.community;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "study_community_bookmark")
@Entity
public class StudyCommunityBookmarkEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_bookmark_seq")
    private Long sequence;

    @Column(name = "member_sequence")
    private Long memberSequence;

    @Column(name = "community_sequence")
    private Long communitySequence;

    @Column
    private LocalDateTime createdTime;

    @Builder
    public StudyCommunityBookmarkEntity(Long memberSequence, Long communitySequence) {
        this.memberSequence = memberSequence;
        this.communitySequence = communitySequence;
        this.createdTime = LocalDateTime.now();
    }
}
