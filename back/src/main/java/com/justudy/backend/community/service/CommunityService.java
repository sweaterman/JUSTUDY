package com.justudy.backend.community.service;

import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.response.CommunityResponse;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {
    private final CommunityRepository repository;

    @Transactional
    public Long createCommunity(CommunityCreate request) {
        CommunityEntity community = request.toEntity();
        return repository.save(community).getSequence();
    }
    @Transactional
    public CommunityResponse readCommunity(Long communitySequence) {
        CommunityEntity entity = repository.findById(communitySequence)
                .orElseThrow(() -> new CommunityNotFound());
        //조회수 증가
        entity.changeViewCount(entity.getViewCount()+1);
        return CommunityResponse.builder()
                .sequence(entity.getSequence())
                .member_seq(entity.getMember_seq())
                .category_seq(entity.getCategory_seq())
                .title(entity.getTitle())
                .content(entity.getContent())
                .viewCount(entity.getViewCount())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .love(entity.getLove())
                .build();
    }

    public List<CommunityResponse> readAllCommunity() {
        return repository.findAll().stream().map(entity -> CommunityResponse.builder()
                    .sequence(entity.getSequence())
                    .member_seq(entity.getMember_seq())
                    .category_seq(entity.getCategory_seq())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .viewCount(entity.getViewCount())
                    .createdTime(entity.getCreatedTime())
                    .modifiedTime(entity.getModifiedTime())
                    .love(entity.getLove())
                    .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public Long UpdateCommunity(long id, CommunityEdit request) {
        CommunityEntity entity = repository.findById(id)
                .orElseThrow(() -> new CommunityNotFound());
        entity.update(request.getTitle(), request.getContent(), request.getViewCount(), request.getModifiedTime());
        return id;
    }

    @Transactional
    public void deleteCommunity(Long communitySequence) {
        repository.findById(communitySequence)
                .orElseThrow(() -> new CommunityNotFound());
        repository.deleteById(communitySequence);
    }

    private class CommunityNotFound extends IllegalArgumentException {

    }
}
