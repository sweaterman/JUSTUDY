package com.justudy.backend.community.dto.request;

import com.justudy.backend.common.enum_util.EnumModel;

public enum SearchType implements EnumModel {

    NICKNAME("nickname"),
    TITLE("title"),
    CONTENT("content");

    private String value;

    SearchType(String value) {
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
