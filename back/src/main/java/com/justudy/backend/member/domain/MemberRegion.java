package com.justudy.backend.member.domain;

import lombok.Getter;

@Getter
public enum MemberRegion {

    SEOUL("서울"),
    DAEJEON("대전"),
    GUMI("구미"),
    GWANGJU("광주"),
    BUWOOLKYUNG("부울경");

    private final String value;

    MemberRegion(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
