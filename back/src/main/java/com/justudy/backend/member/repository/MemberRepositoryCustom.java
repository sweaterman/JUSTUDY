package com.justudy.backend.member.repository;

import com.justudy.backend.login.dto.response.LoginResult;
import com.justudy.backend.member.domain.MemberEntity;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<MemberEntity> findBySequenceWithJoin(Long sequence);

    Optional<LoginResult> findLoginInfoByUserId(String userId);

    Optional<String> findUserId(String userId);

    Optional<String> findNickname(String nickname);

    Optional<String> findSsafyId(String ssafyId);

    Optional<String> findMmId(String mmId);

    Optional<Long> findSequenceByNickname(String nickname);
}
