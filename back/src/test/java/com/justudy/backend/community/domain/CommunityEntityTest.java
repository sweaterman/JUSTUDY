package com.justudy.backend.community.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CommunityEntityTest {

    @Test
    @DisplayName("게시글 삭제 메소드")
    void deleteCommunity() {
        //given
        CommunityEntity community = createCommunity();
        Boolean before = community.getIsDeleted();

        //when
        community.deleteCommunity();

        //then
        assertThat(before).isFalse();
        assertThat(community.getIsDeleted()).isTrue();
    }



    @Test
    @DisplayName("조회수 증가")
    void addViewCount() {
        //given
        CommunityEntity community = createCommunity();
        Integer beforeViewCount = community.getViewCount();

        //when
        community.addViewCount();
        Integer afterViewCount = community.getViewCount();

        //then
        assertThat(beforeViewCount).isEqualTo(0);
        assertThat(afterViewCount).isEqualTo(1);
    }

    private static CommunityEntity createCommunity() {
        CommunityEntity community = CommunityEntity.builder()
                .title("제목")
                .content("내용")
                .isHighlighted(false)
                .build();
        return community;
    }
}