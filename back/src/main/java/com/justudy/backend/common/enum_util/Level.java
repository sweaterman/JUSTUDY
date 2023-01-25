package com.justudy.backend.common.enum_util;

public enum Level implements EnumModel {

    BEGINNER("초보"),
    MIDDLE("중수"),
    ADVANCED("고수");

    private final String value;

    Level(String value) {
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
