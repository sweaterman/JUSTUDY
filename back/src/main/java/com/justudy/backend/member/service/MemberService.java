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


    }

    public boolean isDuplicatedUserId(String userId) {
        List<MemberEntity> members = memberRepository.findAll();
        return members.stream().anyMatch(member -> member.getUserId().equals(userId));
    }
}
