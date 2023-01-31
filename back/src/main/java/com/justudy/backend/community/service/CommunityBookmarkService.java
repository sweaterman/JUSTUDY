package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.exception.InvalidRequest;
import com.justudy.backend.community.repository.CommunityBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommunityBookmarkService {
    private final CommunityBookmarkRepository bookmarkRepository;

    @Transactional
    public Long createBookmark(Long loginSequence, Long communitySequence) {
        CommunityBookmarkEntity savedBookmark = bookmarkRepository.readBookmark(loginSequence, communitySequence)
                .orElseGet(() -> {
                    CommunityBookmarkEntity newBookmark = createNewBookmark(loginSequence, communitySequence);
                    bookmarkRepository.save(newBookmark);
                    return newBookmark;
                });

        return savedBookmark.getSequence();
    }

    @Transactional
    public void deleteBookmark(Long loginSequence, Long communitySequence) {
        CommunityBookmarkEntity findBookmark = bookmarkRepository.readBookmark(loginSequence, communitySequence)
                .orElseThrow(() -> new InvalidRequest());

        bookmarkRepository.delete(findBookmark);
    }

    private CommunityBookmarkEntity createNewBookmark(Long loginSequence, Long communitySequence) {
        return new CommunityBookmarkEntity(loginSequence, communitySequence);
    }

}
