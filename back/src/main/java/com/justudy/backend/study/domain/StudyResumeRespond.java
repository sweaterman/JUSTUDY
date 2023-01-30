package com.justudy.backend.study.domain;

import com.justudy.backend.common.enum_util.EnumModel;

public enum StudyResumeRespond implements EnumModel {

    WAITING("대기중"),
    ACCEPT("수락"),
    REJECT("거절");

    private final String value;

    StudyResumeRespond(String value) {
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
