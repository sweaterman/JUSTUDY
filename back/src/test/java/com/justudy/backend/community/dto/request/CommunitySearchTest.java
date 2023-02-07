package com.justudy.backend.community.dto.request;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CommunitySearchTest {

    @Test
    @DisplayName("Equal 비교")
    void isEqual() {
        CommunitySearch first = new CommunitySearch(0L, 20L, null, null, null, "like");
        CommunitySearch second = new CommunitySearch(0L, 20L, null, null, null, "like");

        assertThat(first).isEqualTo(second);
    }

    @Test
    @DisplayName("String convert to EnumType")
    void convertToEnum() {
        CommunitySearch condition = new CommunitySearch(10L, 30L,
                "backend", "nickname",
                "ssafy", "view");
        System.out.println(condition);
        System.out.println(condition.getType().getKey());
        System.out.println(condition.getType().getValue());
    }

    @Test
    @DisplayName("생성자 확인")
    void testConstructor() {
        //given
        CommunitySearch condition = new CommunitySearch(5L, 2L, "Go",
                "nickname", "hi", "like");
        //expected
        assertThat(condition.getPage()).isEqualTo(5L);
        assertThat(condition.getSize()).isEqualTo(2L);
        assertThat(condition.getCategory()).isEqualTo("Go");
        assertThat(condition.getType()).isEqualTo(SearchType.NICKNAME);
        assertThat(condition.getSearch()).isEqualTo("hi");
        assertThat(condition.getOrder()).isEqualTo(SearchOrderType.LIKE);
    }

    @DisplayName("getOffset")
    @ParameterizedTest
    @CsvSource(value = {"1, 20, 0", "2, 10, 10,", "5, 5, 20", "0, 30, 0", "-1, 5, 0", "5, 5000, 8000"})
    void getOffset(Long page, Long size, Long offset) {
        //given
        CommunitySearch condition = CommunitySearch.builder()
                .page(page)
                .size(size)
                .build();

        //expected
        assertThat(condition.getOffset()).isEqualTo(offset);
    }

    @DisplayName("offset(noticeSize) 테스트")
    @ParameterizedTest
    @CsvSource({"1, 20, 0", "2, 20, 17", "3, 20, 34"})
    void getOffsetWithNoticeSize(Long page, Long size, Long offset) {
        //given
        int noticeSize = 3;
        CommunitySearch condition = CommunitySearch.builder()
                .page(page)
                .size(size)
                .build();

        //expected
        assertThat(condition.getOffsetWithNotice(noticeSize)).isEqualTo(offset);
    }
}