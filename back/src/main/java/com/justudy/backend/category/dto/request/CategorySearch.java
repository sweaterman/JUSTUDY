package com.justudy.backend.category.dto.request;

import lombok.Data;

@Data
public class CategorySearch {

    private String mainKey;

    public CategorySearch(String mainKey) {
        this.mainKey = mainKey;
    }
}
