package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.exception.BookmarkNotFound;
import com.justudy.backend.community.repository.CommunityBookmarkRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CommunityBookmarkServiceTest {

    private final Long LOGIN_SEQUENCE = 1L;
    private final Long COMMUNITY_SEQUENCE = 5L;
    private final Long BOOKMARK_SEQUENCE = 10L;

    private CommunityBookmarkRepository bookmarkRepository = mock(CommunityBookmarkRepository.class);

    private CommunityRepository communityRepository = mock(CommunityRepository.class);

    private CommunityBookmarkService bookmarkService;

    @BeforeEach
    void setUp() {
        bookmarkService = new CommunityBookmarkService(bookmarkRepository, communityRepository);
    }

    @Transactional
    @Test
    @DisplayName("북마크 생성")
    void createBookmark() {
        //given
        CommunityBookmarkEntity newBookmark = new CommunityBookmarkEntity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);
        ReflectionTestUtils.setField(newBookmark, "sequence", BOOKMARK_SEQUENCE, Long.class);

        BDDMockito.given(bookmarkRepository.findBookmark(anyLong(), anyLong()))
                .willReturn(Optional.empty());
        BDDMockito.given(bookmarkRepository.save(any(CommunityBookmarkEntity.class)))
                .willReturn(newBookmark);

        //when
        Long bookmarkSequence = bookmarkService.createBookmark(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);

        //then
        assertThat(bookmarkSequence).isEqualTo(BOOKMARK_SEQUENCE);
        BDDMockito.then(bookmarkRepository).should(times(1)).save(any(CommunityBookmarkEntity.class));
    }

    @Transactional
    @Test
    @DisplayName("북마크 삭제")
    void deleteBookmark() {
        //given
        CommunityBookmarkEntity newBookmark = new CommunityBookmarkEntity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);
        ReflectionTestUtils.setField(newBookmark, "sequence", BOOKMARK_SEQUENCE, Long.class);

        BDDMockito.given(bookmarkRepository.findBookmark(anyLong(), anyLong()))
                .willReturn(Optional.of(newBookmark));
        BDDMockito.willDoNothing().given(bookmarkRepository).delete(newBookmark);

        //when
        bookmarkService.deleteBookmark(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);

        //then
        BDDMockito.then(bookmarkRepository).should(times(1)).findBookmark(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);
        BDDMockito.then(bookmarkRepository).should(times(1)).delete(newBookmark);
    }

    @Transactional
    @Test
    @DisplayName("북마크 삭제 실패 - NotFound")
    void deleteBookmarkNotFoundException() {
        //given
        final Long WRONG_LOGIN = 100L;
        final Long WRONG_COMMUNITY = 1000L;

        CommunityBookmarkEntity newBookmark = new CommunityBookmarkEntity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);
        ReflectionTestUtils.setField(newBookmark, "sequence", BOOKMARK_SEQUENCE, Long.class);

        BDDMockito.given(bookmarkRepository.findBookmark(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE))
                .willReturn(Optional.of(newBookmark));
        BDDMockito.willDoNothing().given(bookmarkRepository).delete(newBookmark);

        //expected
        assertThatThrownBy(() -> bookmarkService.deleteBookmark(WRONG_LOGIN, COMMUNITY_SEQUENCE))
                .isInstanceOf(BookmarkNotFound.class);
        assertThatThrownBy(() -> bookmarkService.deleteBookmark(LOGIN_SEQUENCE, WRONG_COMMUNITY))
                .isInstanceOf(BookmarkNotFound.class);
    }

    @Transactional
    @Test
    @DisplayName("게시글 관련된 북마크 전체 삭제")
    void deleteAllByCommunity() {
        //given
        BDDMockito.willDoNothing()
                .given(bookmarkRepository).deleteAllByCommunity(COMMUNITY_SEQUENCE);
        BDDMockito.willDoNothing()
                .given(bookmarkRepository).deleteAllByCommunity(1000L);
        BDDMockito.willDoNothing()
                .given(bookmarkRepository).deleteAllByCommunity(1001L);

        //when
        bookmarkService.deleteAllByCommunity(COMMUNITY_SEQUENCE);

        //then
        BDDMockito.then(bookmarkRepository).should(only()).deleteAllByCommunity(COMMUNITY_SEQUENCE);
    }
}