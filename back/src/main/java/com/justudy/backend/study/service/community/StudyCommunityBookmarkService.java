package com.justudy.backend.study.service.community;

import com.justudy.backend.community.exception.BookmarkNotFound;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.study.domain.community.StudyCommunityBookmarkEntity;
import com.justudy.backend.study.repository.community.StudyCommunityBookmarkRepository;
import com.justudy.backend.study.repository.community.StudyCommunityRepository;
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
public class StudyCommunityBookmarkService {
    private final StudyCommunityBookmarkRepository bookmarkRepository;

    private final StudyCommunityRepository communityRepository;

    @Transactional
    public Long createBookmark(Long loginSequence, Long communitySequence, Long studySequence) {
        StudyCommunityBookmarkEntity savedBookmark = bookmarkRepository.findBookmark(loginSequence, communitySequence)
                .orElseGet(() ->
                    bookmarkRepository.save(makeNewBookmark(loginSequence, communitySequence))
                );
        return savedBookmark.getSequence();
    }

    @Transactional
    public void deleteBookmark(Long loginSequence, Long communitySequence, Long studySequence) {
        communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        StudyCommunityBookmarkEntity findBookmark = bookmarkRepository.findBookmark(loginSequence, communitySequence)
                .orElseThrow(BookmarkNotFound::new);

        bookmarkRepository.delete(findBookmark);
    }

    @Transactional
    public void deleteBookmarkByCommunity(Long communitySequence) {
        bookmarkRepository.deleteAllByCommunity(communitySequence);
    }

    public List<Long> getMyBookmarks(Long loginSequence) {
        return bookmarkRepository.findCommunitySequence(loginSequence);
    }

    public Optional<StudyCommunityBookmarkEntity> findBookmark(Long loginSequence , Long communitySequence) {
        return bookmarkRepository.findBookmark(loginSequence, communitySequence);
    }

    private StudyCommunityBookmarkEntity makeNewBookmark(Long loginSequence, Long communitySequence) {
        return new StudyCommunityBookmarkEntity(loginSequence, communitySequence);
    }

}
