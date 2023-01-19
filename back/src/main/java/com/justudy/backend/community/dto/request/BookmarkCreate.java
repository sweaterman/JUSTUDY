package com.justudy.backend.community.dto.request;

import com.justudy.backend.community.domain.BookmarkEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class BookmarkCreate {
    private CommunityEntity community;
    private String memberSeq;
    public BookmarkEntity toEntity() {
        return BookmarkEntity.builder()
                .community(community)
                .memberSeq(memberSeq)
                .isChecked(true)
                .createdTime(LocalDateTime.now())
                .build();
    }
}
