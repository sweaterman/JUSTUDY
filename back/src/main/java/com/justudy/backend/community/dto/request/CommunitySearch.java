package com.justudy.backend.community.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class CommunitySearch {

    private static final Long DEFAULT_PAGE = 1L;
    private static final Long DEFAULT_SIZE = 20L;

    private static final Long NOTICE_SIZE = 3L;
    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Long page = 1L;

    @Builder.Default
    private Long size= 20L;

    private String category;

    private String type;

    private String search;

    private String order;

    public CommunitySearch(Long page, Long size, String category, String type, String search, String order) {
        this.page = page;
        this.size = size;
        this.category = category;
        this.type = type;
        this.search = search;
        this.order = order;
    }

    public CommunitySearch validateNull() {
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
    public Long getOffsetWithNotice(int listSize) {
        return (Math.max(1, page) - 1) * Math.min(size - listSize, MAX_SIZE);
    }

    public Long getNoticeBoardSize() {
        return NOTICE_SIZE;
    }
}
