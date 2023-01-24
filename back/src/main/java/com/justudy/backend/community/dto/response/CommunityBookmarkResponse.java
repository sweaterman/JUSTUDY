package com.justudy.backend.community.dto.response;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityBookmarkResponse {
    private Long sequence;
    private MemberEntity member;
    private CommunityEntity community;
    private LocalDateTime createdTime;

    public static CommunityBookmarkResponse makeBuilder(CommunityEntity entity,MemberEntity member) {
        return CommunityBookmarkResponse.builder()
                .sequence(entity.getSequence())
                .community(entity)
                .member(member)
                .createdTime(entity.getCreatedTime())
                .build();
    }
}
