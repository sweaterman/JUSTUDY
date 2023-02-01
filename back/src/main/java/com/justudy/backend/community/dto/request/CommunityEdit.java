package com.justudy.backend.community.dto.request;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CommunityEdit {
    private String title;
    private String content;
    private String category;
}
