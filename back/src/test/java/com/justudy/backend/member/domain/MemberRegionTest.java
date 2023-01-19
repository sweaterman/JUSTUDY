package com.justudy.backend.member.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberRegionTest {

    @Test
    @DisplayName("String to Enum")
    void stringToEnum() {
        //given
        String region = "SEOUL";

        //when
        MemberRegion regionEnum = MemberRegion.valueOf(region);

        //then
        Assertions.assertThat(regionEnum).isEqualTo(MemberRegion.SEOUL);
    }
}
