package com.justudy.backend.category.dto.request;

import com.justudy.backend.category.domain.CategoryEntity;
import lombok.Data;

@Data
public class CategoryResponse {
    private String key;

    private String value;

    public CategoryResponse(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public CategoryResponse(CategoryEntity category) {
        this.key = category.getKey();
        this.value = category.getValue();
    }
}
