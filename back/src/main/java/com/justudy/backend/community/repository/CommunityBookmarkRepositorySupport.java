package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;

import java.util.List;
import java.util.Optional;

public interface CommunityBookmarkRepositorySupport {

    Optional<CommunityBookmarkEntity> readBookmark(Long loginSequence, Long communitySequence);
}
