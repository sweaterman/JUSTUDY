package com.justudy.backend.community.dto.request;

import com.justudy.backend.common.enum_util.EnumModel;
import com.justudy.backend.community.domain.QCommunityEntity;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Function;

import static com.justudy.backend.community.domain.QCommunityEntity.*;

public enum SearchType implements EnumModel {

    NICKNAME("nickname", str -> communityEntity.member.nickname.contains(str)),
    TITLE("title", str -> communityEntity.title.contains(str)),
    CONTENT("content", str -> communityEntity.content.contains(str));

    private String value;

    private Function<String, BooleanExpression> expression;

    SearchType(String value, Function<String, BooleanExpression> expression) {
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
