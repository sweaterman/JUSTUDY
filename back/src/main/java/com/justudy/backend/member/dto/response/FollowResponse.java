package com.justudy.backend.member.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FollowResponse {

    private Long memberSequence;

    private String nickname;

    private Long imageSequence;

    public FollowResponse(Long memberSequence, String nickname, Long imageSequence) {
        this.memberSequence = memberSequence;
        this.nickname = nickname;
        this.imageSequence = imageSequence;
    }
}
