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
    private Long sequence;
    private MemberEntity member;
    private Long category_seq;
    private String title;
    private String content;
}
