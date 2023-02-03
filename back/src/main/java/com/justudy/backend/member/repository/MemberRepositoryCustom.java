package com.justudy.backend.member.repository;

import com.justudy.backend.member.domain.MemberEntity;
import com.querydsl.core.Tuple;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<MemberEntity> findBySequenceWithJoin(Long sequence);

    Optional<Tuple> findPasswordByUserId(String userId);

    Optional<String> findUserId(String userId);

    Optional<String> findNickname(String nickname);

    Optional<String> findSsafyId(String ssafyId);

    Optional<Long> findSequenceByNickname(String nickname);
}
