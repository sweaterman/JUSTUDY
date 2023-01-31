package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.repository.CommunityBookmarkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

class CommunityBookmarkServiceTest {

    private CommunityBookmarkRepository bookmarkRepository = Mockito.mock(CommunityBookmarkRepository.class);

    private CommunityBookmarkService bookmarkService;

    @BeforeEach
    void setUp() {
        bookmarkService = new CommunityBookmarkService(bookmarkRepository);
    }

    @Test
    @DisplayName("북마크 생성")
    void createBookmark() {
        //given
        final Long LOGIN_SEQUENCE = 1L;
        final Long COMMUNITY_SEQUENCE = 1L;
        final Long BOOKMARK_SEQUENCE = 10L;

        CommunityBookmarkEntity newBookmark = bookmarkRepository.save(new CommunityBookmarkEntity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE));
        ReflectionTestUtils.setField(newBookmark, "sequence", BOOKMARK_SEQUENCE);

//        BDDMockito.given(bookmarkRepository.readBookmark(2L, 2L))
//                .willReturn(Optional.of(new CommunityBookmarkEntity(2L, 2L)));

        BDDMockito.given(bookmarkRepository.save(newBookmark))
                .willReturn(newBookmark);

        //when
        Long bookmarkSequence = bookmarkService.createBookmark(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);

        //then
        Assertions.assertThat(bookmarkSequence).isEqualTo(BOOKMARK_SEQUENCE);

    }
}