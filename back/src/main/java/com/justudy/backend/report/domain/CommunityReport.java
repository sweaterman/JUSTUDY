package com.justudy.backend.report.domain;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("community")
@Entity
public class CommunityReport extends Report {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_seq")
    private CommunityEntity targetCommunity;

    public CommunityReport(MemberEntity reporter, String content,
                           CommunityEntity targetCommunity) {
        super(reporter, targetCommunity.getSequence(), content);
        this.targetCommunity = targetCommunity;
    }
}
