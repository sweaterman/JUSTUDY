package com.justudy.backend.study.dto.request.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudyCommunityEdit {
    private String title;
    private String content;
    private String studySeq;
}
