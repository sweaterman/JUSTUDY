package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.exception.BookmarkNotFound;
import com.justudy.backend.community.repository.CommunityBookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommunityBookmarkService {
    private final CommunityBookmarkRepository bookmarkRepository;

    @Transactional
    public Long createBookmark(Long loginSequence, Long communitySequence) {
        CommunityBookmarkEntity savedBookmark = bookmarkRepository.findBookmark(loginSequence, communitySequence)
                .orElseGet(() ->
                    bookmarkRepository.save(makeNewBookmark(loginSequence, communitySequence))
                );

        return savedBookmark.getSequence();
    }

    @Transactional
    public void deleteBookmark(Long loginSequence, Long communitySequence) {
        CommunityBookmarkEntity findBookmark = bookmarkRepository.findBookmark(loginSequence, communitySequence)
                .orElseThrow(BookmarkNotFound::new);

        bookmarkRepository.delete(findBookmark);
    }

    @Transactional
    public void deleteBookmarkByCommunity(Long communitySequence) {
        bookmarkRepository.deleteAllByCommunity(communitySequence);
    }

    private CommunityBookmarkEntity makeNewBookmark(Long loginSequence, Long communitySequence) {
        return new CommunityBookmarkEntity(loginSequence, communitySequence);
    }

}
