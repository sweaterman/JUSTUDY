package com.justudy.backend.member.service;

import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.domain.MemberFollow;
import com.justudy.backend.member.repository.FollowRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class FollowServiceTest {

    final Long LOGIN_SEQUENCE = 10L;
    final Long TO_SEQUENCE = 100L;
    final Long FOLLOW_SEQUENCE = 1000L;

    private FollowService followService;

    private MemberService memberService = mock(MemberService.class);

    private FollowRepository followRepository = mock(FollowRepository.class);

    @BeforeEach
    void setUp() {
        followService = new FollowService(memberService, followRepository);
    }

    @Test
    @DisplayName("팔로우 생성")
    void AddFollow() {
        //given
        MemberEntity fromMember = makeTestMember("FROM", "테스트From", "테스트From");
        ReflectionTestUtils.setField(fromMember, "sequence", LOGIN_SEQUENCE);

        MemberEntity toMember = makeTestMember("TO", "테스트To", "테스트To");
        ReflectionTestUtils.setField(toMember, "sequence", TO_SEQUENCE);

        MemberFollow follow = new MemberFollow(fromMember, toMember);
        ReflectionTestUtils.setField(follow, "sequence", FOLLOW_SEQUENCE);

        BDDMockito.given(memberService.getMember(LOGIN_SEQUENCE))
                .willReturn(fromMember);
        BDDMockito.given(memberService.getMember(TO_SEQUENCE))
                .willReturn(toMember);
        BDDMockito.given(followRepository.save(any(MemberFollow.class)))
                .willReturn(follow);

        //when
        Long savedFollow = followService.saveFollow(LOGIN_SEQUENCE, TO_SEQUENCE);

        //then
        assertThat(savedFollow).isEqualTo(FOLLOW_SEQUENCE);
        BDDMockito.then(memberService).should(times(2)).getMember(any());
        BDDMockito.then(followRepository).should(times(1)).save(any(MemberFollow.class));
    }

    @Test
    @DisplayName("팔로우 삭제")
    void deleteFollow() {
        //given
        MemberEntity fromMember = makeTestMember("FROM", "테스트From", "테스트From");
        ReflectionTestUtils.setField(fromMember, "sequence", LOGIN_SEQUENCE);

        MemberEntity toMember = makeTestMember("TO", "테스트To", "테스트To");
        ReflectionTestUtils.setField(toMember, "sequence", TO_SEQUENCE);

        MemberFollow follow = new MemberFollow(fromMember, toMember);
        ReflectionTestUtils.setField(follow, "sequence", FOLLOW_SEQUENCE);

        BDDMockito.given(followRepository.getFollowByFromAndTo(LOGIN_SEQUENCE, TO_SEQUENCE))
                .willReturn(Optional.of(follow));
        BDDMockito.willDoNothing().given(followRepository).delete(follow);

        ///when
        Long deletedSequence = followService.deleteFollow(LOGIN_SEQUENCE, TO_SEQUENCE);

        //then
        assertThat(deletedSequence).isEqualTo(FOLLOW_SEQUENCE);
        BDDMockito.then(followRepository).should(times(1)).getFollowByFromAndTo(LOGIN_SEQUENCE, TO_SEQUENCE);
        BDDMockito.then(followRepository).should(times(1)).delete(follow);
    }

    private MemberEntity makeTestMember(String userId, String nickname, String ssafyId) {
        return MemberEntity.builder()
                .userId(userId)
                .password("1234")
                .username("이신광")
                .nickname(nickname)
                .ssafyId(ssafyId)
                .phone("01011111111")
                .email("ssafylee@ssafy.com")
                .region(Region.SEOUL)
                .dream("백엔드 취업희망")
                .introduction("안녕하세요")
                .build();
    }
}