package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityLoveEntity;
import com.justudy.backend.community.exception.LoveNotFound;
import com.justudy.backend.community.repository.CommunityLoveRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.BDDMockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CommunityLoveServiceTest {

    private final Long LOGIN_SEQUENCE = 1L;
    private final Long COMMUNITY_SEQUENCE = 5L;
    private final Long LOVE_SEQUENCE = 10L;

    private CommunityLoveService loveService;

    private CommunityLoveRepository loveRepository = mock(CommunityLoveRepository.class);

    private CommunityRepository communityRepository = mock(CommunityRepository.class);

    @BeforeEach
    void setUp() {
        loveService = new CommunityLoveService(loveRepository, communityRepository);
    }

    @Transactional
    @Test
    @DisplayName("좋아요 생성")
    void createLove() {
        //given
        CommunityLoveEntity newLove = new CommunityLoveEntity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);
        ReflectionTestUtils.setField(newLove, "sequence", LOVE_SEQUENCE, Long.class);

        BDDMockito.given(loveRepository.findLove(anyLong(), anyLong()))
                .willReturn(Optional.empty());
        BDDMockito.given(loveRepository.save(any(CommunityLoveEntity.class)))
                .willReturn(newLove);

        //when
        Long loveSequence = loveService.createLove(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);

        //then
        assertThat(loveSequence).isEqualTo(LOVE_SEQUENCE);
        BDDMockito.then(loveRepository).should(times(1)).findLove(anyLong(), anyLong());
        BDDMockito.then(loveRepository).should(times(1)).save(any(CommunityLoveEntity.class));
    }

    @Transactional
    @Test
    @DisplayName("좋아요 삭제")
    void deleteLove() {
        //given
        CommunityLoveEntity newLove = new CommunityLoveEntity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);
        ReflectionTestUtils.setField(newLove, "sequence", LOVE_SEQUENCE, Long.class);

        BDDMockito.given(loveRepository.findLove(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE))
                .willReturn(Optional.of(newLove));
        BDDMockito.willDoNothing().given(loveRepository).delete(newLove);

        //when
        loveService.deleteLove(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);

        //then
        BDDMockito.then(loveRepository).should(times(1)).findLove(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);
        BDDMockito.then(loveRepository).should(times(1)).delete(newLove);
    }

    @Transactional
    @Test
    @DisplayName("좋아요 삭제 실패 - NotFound")
    void deleteLoveNotFoundException() {
        //given
        final Long WRONG_LOGIN = 100L;
        final Long WRONG_COMMUNITY = 1000L;

        CommunityLoveEntity newLove = new CommunityLoveEntity(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE);
        ReflectionTestUtils.setField(newLove, "sequence", LOVE_SEQUENCE, Long.class);

        BDDMockito.given(loveRepository.findLove(LOGIN_SEQUENCE, COMMUNITY_SEQUENCE))
                .willReturn(Optional.of(newLove));
        BDDMockito.willDoNothing().given(loveRepository).delete(newLove);

        //expected
        assertThatThrownBy(() -> loveService.deleteLove(WRONG_LOGIN, COMMUNITY_SEQUENCE))
                .isInstanceOf(LoveNotFound.class);
        assertThatThrownBy(() -> loveService.deleteLove(LOGIN_SEQUENCE, WRONG_COMMUNITY))
                .isInstanceOf(LoveNotFound.class);
    }


    @ParameterizedTest
    @DisplayName("좋아요 개수 체크")
    @ValueSource(longs = {1, 2, 3, 4, 5})
    void CountLoveTest(long communitySequence) {
        //given
        final Integer LOVE_COUNTS = 10;
        for (long i = 1; i <= 5; i++) {
            BDDMockito.given(loveRepository.countOfLove(i))
                    .willReturn(LOVE_COUNTS);
        }
        //when
        Integer countOfLove = loveService.getCountOfLove(communitySequence);

        //expected
        assertThat(countOfLove).isEqualTo(LOVE_COUNTS);
        BDDMockito.then(loveRepository).should(only()).countOfLove(communitySequence);
    }
}