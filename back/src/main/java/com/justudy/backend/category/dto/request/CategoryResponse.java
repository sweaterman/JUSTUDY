package com.justudy.backend.category.dto.request;

import com.justudy.backend.category.domain.CategoryEntity;
import lombok.Data;

@Data
public class CategoryResponse {
    private String key;

    private String value;

    private Long imageSequence;

    public CategoryResponse(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public CategoryResponse(String key, String value, Long imageSequence) {
        this.key = key;
        this.value = value;
        this.imageSequence = imageSequence;
    }

    public CategoryResponse(CategoryEntity category) {
        this.key = category.getKey();
        this.value = category.getValue();
        this.imageSequence = (category.getImageFile() != null ? category.getImageFile().getSequence() : 9999L);
    }
}
