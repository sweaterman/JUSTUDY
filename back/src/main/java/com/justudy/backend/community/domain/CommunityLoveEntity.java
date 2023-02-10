package com.justudy.backend.community.domain;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "community_love")
@Entity
public class CommunityLoveEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "love_seq")
    private Long sequence;

    @Column(name = "member_seq")
    private Long memberSequence;

    @Column(name = "community_seq")
    private Long communitySequence;

    @Column(name = "love_created_time")
    private LocalDateTime createdTime;

    public CommunityLoveEntity(Long memberSequence, Long communitySequence) {
        this.memberSequence = memberSequence;
        this.communitySequence = communitySequence;
        this.createdTime = LocalDateTime.now();
    }
}
