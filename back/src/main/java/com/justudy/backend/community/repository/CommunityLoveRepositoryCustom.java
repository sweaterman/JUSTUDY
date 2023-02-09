package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityLoveEntity;

import java.util.List;
import java.util.Optional;

public interface CommunityLoveRepositoryCustom {

    Optional<CommunityLoveEntity> findLove(Long loginSequence, Long communitySequence);

    void deleteAllByCommunity(Long communitySequence);

    Integer countOfLove(Long communitySequence);

    List<Long> findCommunitySequence(Long loginSequence);
}
