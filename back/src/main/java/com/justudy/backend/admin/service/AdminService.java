package com.justudy.backend.admin.service;

import com.justudy.backend.admin.dto.request.MemberSearch;
import com.justudy.backend.admin.dto.response.MemberListResponse;
import com.justudy.backend.admin.dto.response.MemberListResult;
import com.justudy.backend.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public MemberListResult<MemberListResponse> getMembers(MemberSearch memberSearch) {
        List<MemberListResponse> membersBySearch = adminRepository.getMembers(memberSearch).stream()
                .map(MemberListResponse::new)
                .collect(Collectors.toList());
        Long totalCount = adminRepository.getCountMemberBySearch(memberSearch);

        return new MemberListResult(membersBySearch, totalCount);
    }

    public Long getCountOfMembers() {
        return adminRepository.getCountOfMembers();
    }
}
