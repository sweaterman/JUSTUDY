package com.justudy.backend.common.validate;

import com.justudy.backend.exception.ForbiddenRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.domain.MemberRole;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    public static void validateUserRole(MemberEntity member, MemberRole role) {
        if (member.getRole().equals(role.getKey())) {
            throw new ForbiddenRequest();
        }
    }
}
