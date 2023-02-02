package com.justudy.backend.community.dto.request;

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

    public CommunitySearch(Long page, Long size,
                           String category, String type, String search,
                           String order) {
        this.page = isPageNull(page);
        this.size = isSizeNull(size);
        this.category = category;
        this.type = type;
        this.search = search;
        this.order = order;
    }
    public CommunitySearch(CommunitySearchRequest request) {
        this.page = isPageNull(request.getPage());
        this.size = isSizeNull(request.getSize());
        this.category = request.getCategory();
        this.type = request.getType();
        this.search = request.getSearch();
        this.order = request.getOrder();
    }

    private static Long isPageNull(Long page) {
        if (page == null) {
            return DEFAULT_PAGE;
        }
        return page;
    }
    private static Long isSizeNull(Long size) {
        if (size == null) {
            return DEFAULT_SIZE;
        }
        return size;
    }

    public static CommunitySearch createCondition(CommunitySearchRequest request) {
        return CommunitySearch.builder()
                .page(request.getPage())
                .size(request.getSize())
                .category(request.getCategory())
                .type(request.getType())
                .search(request.getSearch())
                .order(request.getOrder())
                .build();
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
