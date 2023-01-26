package com.justudy.backend.member.repository;

import com.querydsl.core.Tuple;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Tuple> findPasswordByUserId(String userId);


}
