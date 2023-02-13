package com.justudy.backend.admin.dto.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class MemberSearch {

    private static final Long DEFAULT_PAGE = 0L;

    private static final Long DEFAULT_SIZE = 20L;

    private static final Long MAX_SIZE = 2000L;

    private Long page;

    private Long size;

    private MemberSearchType type;

    private String search;

    public MemberSearch validateNull() {
        if (page == null) {
            page = DEFAULT_PAGE;
        }
        if (size == null) {
            size = DEFAULT_SIZE;
        }
        return this;
    }

    public Long getOffset() {
        return (Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }
}
