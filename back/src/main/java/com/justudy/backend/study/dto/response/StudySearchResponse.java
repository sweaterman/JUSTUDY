package com.justudy.backend.study.dto.response;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
