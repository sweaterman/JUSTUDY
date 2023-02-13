package com.justudy.backend.study.domain.community;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "study_community_love")
@Entity
public class StudyCommunityLoveEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_love_seq")
    private Long sequence;

    @Column(name = "member_sequence")
    private Long memberSequence;

    @Column(name = "study_community_sequence")
    private Long communitySequence;

    @Column
    private LocalDateTime createdTime;

    public StudyCommunityLoveEntity(Long memberSequence, Long communitySequence) {
        this.memberSequence = memberSequence;
        this.communitySequence = communitySequence;
        this.createdTime = LocalDateTime.now();
    }
}
