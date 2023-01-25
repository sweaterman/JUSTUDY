package com.justudy.backend.common.enum_util;

import lombok.Getter;

@Getter
public class EnumValue {

    private String key;

    private String value;

    public EnumValue(EnumModel enumModel) {
        key = enumModel.getKey();
        value = enumModel.getValue();
    }
}
