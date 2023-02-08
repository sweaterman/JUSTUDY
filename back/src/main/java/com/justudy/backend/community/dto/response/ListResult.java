package com.justudy.backend.community.dto.response;

import lombok.Data;

@Data
public class ListResult<T> {

    private T communityList;

    private Long totalCount;

    public ListResult(T communityList, Long totalCount) {
        this.communityList = communityList;
        this.totalCount = totalCount;
    }
}
