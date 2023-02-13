package com.justudy.backend.admin.dto.response;

import lombok.Data;

@Data
public class MemberListResult<T> {

    private T memberList;

    private Long totalCount;

    public MemberListResult(T memberList, Long totalCount) {
        this.memberList = memberList;
        this.totalCount = totalCount;
    }
}
