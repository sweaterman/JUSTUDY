package com.justudy.backend.member.domain;

import com.justudy.backend.common.enum_util.Region;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegionTest {

    @Test
    @DisplayName("String to Enum")
    void stringToEnum() {
        //given
        String region = "SEOUL";

        //when
        Region regionEnum = Region.valueOf(region);

        //then
        Assertions.assertThat(regionEnum).isEqualTo(Region.SEOUL);
    }
}
