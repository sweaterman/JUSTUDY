package com.justudy.backend.admin.dto.request;

import com.justudy.backend.common.enum_util.EnumModel;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Function;

import static com.justudy.backend.member.domain.QMemberEntity.memberEntity;

public enum MemberSearchType implements EnumModel {

    MEMBER_ID("userid", str -> memberEntity.userId.contains(str)),
    SSAFY_ID("ssafy-id", str -> memberEntity.ssafyId.contains(str)),
    MEMBER_NAME("name", str -> memberEntity.username.contains(str)),
    NICKNAME("nickname", str -> memberEntity.nickname.contains(str));

    private String value;

    private Function<String, BooleanExpression> expression;

    MemberSearchType(String value, Function<String, BooleanExpression> expression) {
        this.value = value;
        this.expression = expression;
    }

    public BooleanExpression getExpression(String keyword) {
        return expression.apply(keyword);
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
