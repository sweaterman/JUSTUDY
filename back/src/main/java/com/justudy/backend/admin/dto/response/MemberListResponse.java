package com.justudy.backend.admin.dto.response;

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

    private LocalDateTime createdTime;
}
