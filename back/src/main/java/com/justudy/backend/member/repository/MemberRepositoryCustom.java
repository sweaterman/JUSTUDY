package com.justudy.backend.member.repository;

import com.querydsl.core.Tuple;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Tuple> findPasswordByUserId(String userId);

    Optional<String> findUserId(String userId);

    Optional<String> findNickname(String nickname);

    Optional<String> findSsafyId(String ssafyId);
}
