package com.justudy.backend.common.enum_util;

import lombok.Getter;

@Getter
public enum Region implements EnumModel {

    SEOUL("서울"),
    DAEJEON("대전"),
    GUMI("구미"),
    GWANGJU("광주"),
    BUWOOLKYUNG("부울경");

    private final String value;

    Region(String value) {
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
