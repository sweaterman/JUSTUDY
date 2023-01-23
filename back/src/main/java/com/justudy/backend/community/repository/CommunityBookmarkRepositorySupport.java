package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.dto.request.CommunityBookmarkCreate;

import java.util.List;
import java.util.Optional;

public interface CommunityBookmarkRepositorySupport {
    void saveBookmark(CommunityBookmarkCreate toEntity);

    Optional<CommunityBookmarkEntity> readBookmark(CommunityBookmarkCreate request);

    void updateBookmark(CommunityBookmarkCreate request);

    void deleteBookmark(Long id,Long userId);

    List<CommunityBookmarkEntity> readAllBookmarkByMember(Long userId);
}
