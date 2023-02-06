package com.justudy.backend.community.dto.request;

import com.justudy.backend.common.enum_util.EnumModel;
import com.querydsl.core.types.OrderSpecifier;

import static com.justudy.backend.community.domain.QCommunityEntity.communityEntity;

public enum SearchOrderType implements EnumModel {

    LIKE("like", communityEntity.loveCount.desc()),
    VIEW("view", communityEntity.viewCount.desc());

    private String value;

    private OrderSpecifier<?> orderSpecifier;

    SearchOrderType(String value, OrderSpecifier<?> orderSpecifier) {
        this.value = value;
        this.orderSpecifier = orderSpecifier;
    }

    public OrderSpecifier<?> getOrderSpecifier() {
        return orderSpecifier;
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
