package com.justudy.backend.common.enum_util;

import com.justudy.backend.member.domain.MemberRole;
import com.justudy.backend.member.domain.MemberStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnumConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("level", Level.class);
        enumMapper.put("region", Region.class);
        enumMapper.put("memberRole", MemberRole.class);
        enumMapper.put("memberStatus", MemberStatus.class);
        return enumMapper;
    }
}
