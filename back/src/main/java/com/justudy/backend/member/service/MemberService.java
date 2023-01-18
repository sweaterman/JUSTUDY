package com.justudy.backend.member.service;

import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void createMember(MemberCreate request) {


    public MypageResponse getMemberOfMypage(Long loginSequence) {
        MemberEntity findMember = memberRepository.findById(loginSequence)
                .orElseThrow(() -> new MemberNotFound());

        return createMypageResponse(findMember);
    }

    public boolean isDuplicatedUserId(String userId) {
        List<MemberEntity> members = memberRepository.findAll();
        return members.stream().anyMatch(member -> member.getUserId().equals(userId));
    }

    public boolean isDuplicatedNickname(String nickname) {
        List<MemberEntity> members = memberRepository.findAll();
        return members.stream().anyMatch(member -> member.getNickname().equals(nickname));
    }

    public boolean isDuplicatedSsafyId(String ssafyId) {
        List<MemberEntity> members = memberRepository.findAll();
        return members.stream().anyMatch(member -> member.getSsafyId().equals(ssafyId));
    }

    public boolean isNotEqualPassword(String password, String passwordCheck) {
        return !password.equals(passwordCheck);
    }

    private MypageResponse createMypageResponse(MemberEntity member) {
        return MypageResponse.builder()
                .nickname(member.getNickname())
                .category(member.getCategories())
                .dream(member.getDream())
                .status(member.getStatus().getValue())
                .badgeCount(member.getBadgeCount())
                .level(member.getLevel().getValue())
                .build();
    }
}
