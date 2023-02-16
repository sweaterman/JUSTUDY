package com.justudy.backend.admin.dto.response;

import com.justudy.backend.common.date.DateChanger;
import com.justudy.backend.member.domain.MemberEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberListResponse {

    private Long memberSequence;

    private String userId;

    private String username;

    private String nickname;

    private String ssafyId;

    private String mmId;

    private String createdTime;

    private boolean isBanned;

    private boolean isDeleted;

    @Builder
    public MemberListResponse(Long memberSequence, String userId, String username, String nickname,
                              String ssafyId, String mmId, LocalDateTime createdTime,
                              boolean isBanned, boolean isDeleted) {
        this.memberSequence = memberSequence;
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.ssafyId = ssafyId;
        this.mmId = mmId;
        this.createdTime = DateChanger.format(createdTime);
        this.isBanned = isBanned;
        this.isDeleted = isDeleted;
    }

    public static MemberListResponse toResponse(MemberEntity member) {
        return MemberListResponse.builder()
                .memberSequence(member.getSequence())
                .userId(member.getUserId())
                .username(member.getUsername())
                .nickname(member.getNickname())
                .ssafyId(member.getSsafyId())
                .mmId(member.getMmId())
                .createdTime(member.getCreatedTime())
                .isBanned(member.isBanned())
                .isDeleted(member.isDeleted())
                .build();
    }
}
