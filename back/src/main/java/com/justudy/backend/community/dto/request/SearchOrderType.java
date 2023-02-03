package com.justudy.backend.community.dto.request;

import com.justudy.backend.common.enum_util.EnumModel;

public enum SearchOrderType implements EnumModel {

    LIKE("like"),
    VIEW("view");

    private String value;

    SearchOrderType(String value) {
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
