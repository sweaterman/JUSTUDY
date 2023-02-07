package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;

import java.util.List;
import java.util.Optional;

public interface CommunityBookmarkRepositoryCustom {

    Optional<CommunityBookmarkEntity> findBookmark(Long loginSequence, Long communitySequence);

    void deleteAllByCommunity(Long communitySequence);

    List<Long> findCommunitySequence(Long loginSequence);
}
