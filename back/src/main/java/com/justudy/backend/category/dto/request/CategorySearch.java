package com.justudy.backend.category.dto.request;

import lombok.Data;

@Data
public class CategorySearch {

    private String main;

    public CategorySearch(String main) {
        this.main = main;
    }
}
