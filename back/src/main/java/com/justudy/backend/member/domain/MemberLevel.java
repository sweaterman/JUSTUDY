package com.justudy.backend.member.domain;

public enum MemberLevel {

    BEGINNER("초보"),
    MIDDLE("중수"),
    ADVANCED("고수");

    private final String value;

    MemberLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
