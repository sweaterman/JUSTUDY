package com.justudy.backend.report.dto.request;

import com.justudy.backend.common.enum_util.EnumModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ReportType implements EnumModel {

    MEMBER("member", Arrays.stream(MemberReportReason.class.getEnumConstants()).collect(Collectors.toList())),
    COMMUNITY("community", Arrays.stream(CommunityReportReason.class.getEnumConstants()).collect(Collectors.toList())),
    COMMENT("comment",  Arrays.stream(CommentReportReason.class.getEnumConstants()).collect(Collectors.toList())),
    STUDY("study", Arrays.stream(StudyReportReason.class.getEnumConstants()).collect(Collectors.toList()));

    private String value;

    private List<? extends ReasonModel> reasons;

    ReportType(String value, List<? extends ReasonModel> reasons) {
        this.value = value;
        this.reasons = reasons;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }

    public List<? extends ReasonModel> getReasons() {
        return reasons;
    }
}
