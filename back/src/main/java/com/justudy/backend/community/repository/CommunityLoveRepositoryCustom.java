package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityLoveEntity;

import java.util.Optional;

public interface CommunityLoveRepositoryCustom {

    Optional<CommunityLoveEntity> findLove(Long loginSequence, Long communitySequence);

    void deleteAllByCommunity(Long communitySequence);
}
