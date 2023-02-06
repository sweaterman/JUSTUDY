package com.justudy.backend.community.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
public class CommunitySearchRequest {

    private Long page;

    private Long size;
    private String category;
    private String type;
    private String search;
    private String order;

}