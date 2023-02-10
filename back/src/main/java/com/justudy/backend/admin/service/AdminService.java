package com.justudy.backend.admin.service;

import com.justudy.backend.admin.dto.response.MemberListResponse;
import com.justudy.backend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminService {


    private final MemberRepository memberRepository;

    public List<MemberListResponse> getMembers(Pageable pageable) {
        return null;
    }
}
