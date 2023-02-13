package com.justudy.backend.admin.dto.response;

import lombok.Data;

@Data
public class TotalResult {

    Long total;

    public TotalResult(Long total) {
        this.total = total;
    }
}
