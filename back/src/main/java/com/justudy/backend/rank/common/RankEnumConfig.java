package com.justudy.backend.rank.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RankEnumConfig {

  @Bean
  public RankEnumMapper rankEnumMapper() {
    RankEnumMapper rankEnumMapper = new RankEnumMapper();
    rankEnumMapper.put("rankGroup", RankGroup.class);
    rankEnumMapper.put("rankType", RankType.class);
    return rankEnumMapper;
  }
}
