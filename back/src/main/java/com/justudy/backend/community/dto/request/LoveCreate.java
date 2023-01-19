package com.justudy.backend.community.dto.request;


import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.LoveEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class LoveCreate {
    private CommunityEntity community;

    public LoveEntity toEntity() {
        return LoveEntity.builder().community(community).isChecked(true).build();
    }
}
