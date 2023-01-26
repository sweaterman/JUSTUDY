package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityLoveEntity;
import com.justudy.backend.community.dto.request.CommunityLoveCreate;

import java.util.List;
import java.util.Optional;

public interface CommunityLoveRepositorySupport {

    Long saveLove(CommunityLoveCreate request);

    Long updateLove(CommunityLoveCreate request, boolean flag);

    List<CommunityLoveEntity> readAllLoveByCommunity(Long id);

    Long deleteAllLoveByCommunity(Long id);

    Optional<CommunityLoveEntity> readLove(CommunityLoveCreate request);

    Integer readLoveCountByCommunity(Long communitySequence);
}
