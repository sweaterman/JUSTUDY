package com.justudy.backend.member.domain;

import com.justudy.backend.common.enum_util.EnumModel;

public enum MemberRole implements EnumModel {

    USER("회원"),
    MENTO("멘토"),
    ADMIN("관리자");

    private final String value;

    MemberRole(String value) {
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
