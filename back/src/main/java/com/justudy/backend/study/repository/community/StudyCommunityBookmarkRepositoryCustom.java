package com.justudy.backend.study.repository.community;


import com.justudy.backend.study.domain.community.StudyCommunityBookmarkEntity;

import java.util.List;
import java.util.Optional;

public interface StudyCommunityBookmarkRepositoryCustom {

    Optional<StudyCommunityBookmarkEntity> findBookmark(Long loginSequence, Long communitySequence);

    void deleteAllByCommunity(Long communitySequence);

    List<Long> findCommunitySequence(Long loginSequence);
}
