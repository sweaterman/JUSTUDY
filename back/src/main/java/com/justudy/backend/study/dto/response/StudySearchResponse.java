package com.justudy.backend.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class StudySearchResponse {

    private List<StudyResponse> studyResponse;

    private Boolean checkMore;

    public static StudySearchResponse makeBuilder(List<StudyResponse> studyResponse, Boolean checkMore) {
        return StudySearchResponse.builder()
                .studyResponse(studyResponse)
                .checkMore(checkMore)
                .build();
    }

}
