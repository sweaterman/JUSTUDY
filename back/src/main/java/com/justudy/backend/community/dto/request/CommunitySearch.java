package com.justudy.backend.community.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.justudy.backend.community.exception.SearchOrderTypeNotFound;
import com.justudy.backend.community.exception.SearchTypeNotFound;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Data
public class CommunitySearch {

    private static final Long DEFAULT_PAGE = 1L;
    private static final Long DEFAULT_SIZE = 20L;

    private static final Long NOTICE_SIZE = 3L;
    private static final int MAX_SIZE = 2000;

    private Long page;

    private Long size;

    private String category;

    private SearchType type;

    private String search;

    private SearchOrderType order;

    @Builder
    @JsonCreator
    public CommunitySearch(@JsonProperty("page") Long page,
                           @JsonProperty("size") Long size,
                           @JsonProperty("category") String category,
                           @JsonProperty("type") String type,
                           @JsonProperty("search") String search,
                           @JsonProperty("order") String order) {
        this.page = page;
        this.size = size;
        this.category = category;
        this.type = convertToSearchType(type);
        this.search = search;
        this.order = convertToSearchOrderType(order);
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

    private SearchType convertToSearchType(String type) {
        if (type == null) {
            return null;
        }
        return Arrays.stream(SearchType.values())
                .filter(searchType -> searchType.getValue().equals(type))
                .findFirst()
                .orElseThrow(SearchTypeNotFound::new);
    }

    private SearchOrderType convertToSearchOrderType(String order) {
        if (order == null) {
            return null;
        }
        return Arrays.stream(SearchOrderType.values())
                .filter(searchOrderType -> searchOrderType.getValue().equals(order))
                .findFirst()
                .orElseThrow(SearchOrderTypeNotFound::new);
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
