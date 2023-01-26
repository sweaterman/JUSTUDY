package com.justudy.backend.community.dto.request;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommunityBookmarkCreate {
    private CommunityEntity community;
    private MemberEntity member;

    public CommunityBookmarkEntity toEntity() {
        return CommunityBookmarkEntity.builder()
                .community(community)
                .member(member)
                .isChecked(true)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
