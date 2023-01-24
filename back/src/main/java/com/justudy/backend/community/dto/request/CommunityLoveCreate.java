package com.justudy.backend.community.dto.request;


import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.CommunityLoveEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CommunityLoveCreate {
    private CommunityEntity community;
    private MemberEntity member;

    public CommunityLoveEntity toEntity() {
        return CommunityLoveEntity
                .builder()
                .community(community)
                .member(member)
                .isChecked(true)
                .build();
    }
}
