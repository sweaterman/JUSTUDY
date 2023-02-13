package com.justudy.backend.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "follow")
@Entity
public class MemberFollow {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_seq")
    private Long sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_seq")
    private MemberEntity fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_seq")
    private MemberEntity toMember;

    @Column(name = "follow_created_time")
    private LocalDateTime createdTime;

    @Builder
    public MemberFollow(MemberEntity fromMember, MemberEntity toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;
        this.createdTime = LocalDateTime.now();
    }
}
