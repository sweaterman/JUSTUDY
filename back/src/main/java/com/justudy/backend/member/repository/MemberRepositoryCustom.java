package com.justudy.backend.member.repository;

import com.querydsl.core.Tuple;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Tuple> hasUserId(String userId);


}
