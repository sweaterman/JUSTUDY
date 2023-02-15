package com.justudy.backend.report.dto.response.admin;

import lombok.Data;

@Data
public class ReportListResult<T> {

    private T reportList;

    private Long totalCount;

    public ReportListResult(T reportList, Long totalCount) {
        this.reportList = reportList;
        this.totalCount = totalCount;
    }
}
