package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.exception.BookmarkNotFound;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.repository.CommunityBookmarkRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommunityBookmarkService {
    private final CommunityBookmarkRepository bookmarkRepository;

    private final CommunityRepository communityRepository;

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
        CommunityEntity community = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        CommunityBookmarkEntity findBookmark = bookmarkRepository.findBookmark(loginSequence, communitySequence)
                .orElseThrow(BookmarkNotFound::new);

        bookmarkRepository.delete(findBookmark);
    }

    @Transactional
    public void deleteAllByCommunity(Long communitySequence) {
        bookmarkRepository.deleteAllByCommunity(communitySequence);
    }

    public List<Long> getMyBookmarks(Long loginSequence) {
        return bookmarkRepository.findCommunitySequence(loginSequence);
    }

    public Optional<CommunityBookmarkEntity> findBookmark(Long loginSequence , Long communitySequence) {
        return bookmarkRepository.findBookmark(loginSequence, communitySequence);
    }

    private CommunityBookmarkEntity makeNewBookmark(Long loginSequence, Long communitySequence) {
        return new CommunityBookmarkEntity(loginSequence, communitySequence);
    }

}
