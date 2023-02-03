package com.justudy.backend.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Builder
public class StudyResumeApply {
    private Boolean isAccept;
}
