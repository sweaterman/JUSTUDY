package com.justudy.backend.member.service;

import com.justudy.backend.member.domain.MemberFollow;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.dto.response.FollowResponse;
import com.justudy.backend.member.exception.FollowNotFound;
import com.justudy.backend.member.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FollowService {

    private final MemberService memberService;

    private final FollowRepository followRepository;

    @Transactional
    public Long saveFollow(Long loginSequence, Long memberSequence) {
        MemberFollow savedFollow = followRepository.getFollowByFromAndTo(loginSequence, memberSequence)
                .orElseGet(() -> {
                    MemberEntity fromMember = memberService.getMember(loginSequence);
                    MemberEntity toMember = memberService.getMember(memberSequence);
                    MemberFollow follow = new MemberFollow(fromMember, toMember);
                    return followRepository.save(follow);
                });

        return savedFollow.getSequence();
    }

    @Transactional
    public Long deleteFollow(Long loginSequence, Long memberSequence) {
        MemberFollow foundFollow = followRepository.getFollowByFromAndTo(loginSequence, memberSequence)
                .orElseThrow(FollowNotFound::new);
        followRepository.delete(foundFollow);
        return foundFollow.getSequence();
    }

    public List<FollowResponse> getMyFollow(Long loginSequence) {
        return followRepository.getMyFollow(loginSequence);
    }

    public List<FollowResponse> getMyFollower(Long loginSequence) {
        return followRepository.getMyFollower(loginSequence);
    }
}
