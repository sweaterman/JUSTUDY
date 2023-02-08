package com.justudy.backend.member.repository;

import com.justudy.backend.member.domain.MemberFollow;
import com.justudy.backend.member.dto.response.FollowResponse;

import java.util.List;
import java.util.Optional;

public interface FollowRepositoryCustom {

    Optional<MemberFollow> getFollowByFromAndTo(Long fromSequence, Long toSequence);

    List<FollowResponse> getMyFollow(Long fromSequence);

    List<FollowResponse> getMyFollower(Long toSequence);
}
