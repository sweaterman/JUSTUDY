package com.justudy.backend.study.repository.community;


import com.justudy.backend.study.domain.community.StudyCommunityLoveEntity;

import java.util.List;
import java.util.Optional;

public interface StudyCommunityLoveRepositoryCustom {

    Optional<StudyCommunityLoveEntity> findLove(Long loginSequence, Long communitySequence);

    void deleteAllByCommunity(Long communitySequence);

    Integer countOfLove(Long communitySequence);

    List<Long> findCommunitySequence(Long loginSequence);
}
