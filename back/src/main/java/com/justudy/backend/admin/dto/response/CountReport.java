package com.justudy.backend.admin.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class CountReport {

    private Long member;

    private Long community;

    private Long comment;

    private Long study;

    @Builder
    public CountReport(Long member, Long community, Long comment, Long study) {
        this.member = member;
        this.community = community;
        this.comment = comment;
        this.study = study;
    }
}
