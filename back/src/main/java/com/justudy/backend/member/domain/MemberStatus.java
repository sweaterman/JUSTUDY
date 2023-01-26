package com.justudy.backend.member.domain;

import com.justudy.backend.common.enum_util.EnumModel;
import lombok.Getter;

@Getter
public enum MemberStatus implements EnumModel {
    EMPTY("자리비움"),
    BUSY("집중모드"),
    OFFLINE("오프라인"),
    ONLINE("온라인");

    private final String value;

    MemberStatus(String value) {
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
