package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.BookmarkEntity;
import com.justudy.backend.community.dto.request.BookmarkCreate;
import com.justudy.backend.community.dto.response.CommunityResponse;

import java.util.List;

public interface BookmarkRepositorySupport {
    void saveBookmark(BookmarkCreate toEntity);

    BookmarkEntity readBookmark(BookmarkCreate request);

    void updateBookmark(BookmarkCreate request);

    void deleteBookmark(Long id,Long userId);

    List<BookmarkEntity> readAllBookmarkByMember(Long userId);
}
