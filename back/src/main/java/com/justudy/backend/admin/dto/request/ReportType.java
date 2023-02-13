package com.justudy.backend.admin.dto.request;

import com.justudy.backend.common.enum_util.EnumModel;

public enum ReportType implements EnumModel {

    MEMBER("member"),
    COMMUNITY("community"),
    COMMENT("comment"),
    STUDY("study");

    private String value;

    ReportType(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
