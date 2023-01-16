package com.justudy.backend.member.domain;

import lombok.Getter;

@Getter
public enum MemberStatus {
    EMPTY("부재"),
    BUSY("바쁨"),
    ON("접속중");

    private final String value;

    MemberStatus(String value) {
        this.value = value;
    }
}
