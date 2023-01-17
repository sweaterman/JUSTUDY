package com.justudy.backend.community.dto.request;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.CommunityLove;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommunityLoveCreate {
    private CommunityEntity community;

    @Builder
    public CommunityLoveCreate(CommunityEntity community) {
        this.community=community;
    }

    public CommunityLove toEntity() {
        return CommunityLove.builder().community(community).build();
    }
}
