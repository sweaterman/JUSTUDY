package com.justudy.backend.community.domain;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "community_love")
@Entity
public class CommunityLoveEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "love_seq")
    private Long sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "community_seq", name = "community_seq")
    private CommunityEntity community;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "member_seq", name = "member_seq")
    private MemberEntity member;

    @Column
    private Boolean isChecked;

}
