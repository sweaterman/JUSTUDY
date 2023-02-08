package com.justudy.backend.study.domain;

import com.justudy.backend.common.enum_util.EnumModel;

public enum StudyFrequencyWeek implements EnumModel {
    월("월"),
    화("화"),
    수("수"),
    목("목"),
    금("금"),
    토("토"),
    일("일");

    private final String value;

    StudyFrequencyWeek(String value) {
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
