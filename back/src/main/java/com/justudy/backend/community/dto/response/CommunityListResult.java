package com.justudy.backend.community.dto.response;

import lombok.Data;

@Data
public class CommunityListResult<T> {

    private T communityList;

    private Long totalCount;

    public CommunityListResult(T communityList, Long totalCount) {
        this.communityList = communityList;
        this.totalCount = totalCount;
    }
}
