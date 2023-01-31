package com.justudy.backend.community.dto.request;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CommunityEdit {
    private String title;
    private String content;
    private String category;
}
