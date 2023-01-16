package com.justudy.backend.service;

import com.justudy.backend.domain.CommunityEntity;
import com.justudy.backend.dto.request.CommunityCreate;
import com.justudy.backend.dto.request.CommunityEdit;
import com.justudy.backend.dto.response.CommunityResponse;
import com.justudy.backend.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
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

    public CommunityResponse readCommunity(Long communitySequence) {
        CommunityEntity entity = repository.findById(communitySequence)
                .orElseThrow(() -> new CommunityNotFound());
        return CommunityResponse.builder()
                .sequence(entity.getSequence())
                .member_seq(entity.getMember_seq())
                .category_seq(entity.getCategory_seq())
                .title(entity.getTitle())
                .content(entity.getContent())
                .viewCount(entity.getViewCount())
                .bookmark(entity.getBookmark())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .love(entity.getLove())
                .isDeleted(entity.getIsDeleted())
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
                    .bookmark(entity.getBookmark())
                    .createdTime(entity.getCreatedTime())
                    .modifiedTime(entity.getModifiedTime())
                    .love(entity.getLove())
                    .isDeleted(entity.getIsDeleted())
                    .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public Long UpdateCommunity(long id, CommunityEdit request) {
        CommunityEntity entity = repository.findById(id)
                .orElseThrow(() -> new CommunityNotFound());
        entity.update(request.getTitle(), request.getContent(), request.getViewCount(), request.getBookmark(), request.getModifiedTime());
        return id;
    }

    @Transactional
    public void deleteCommunity(Long communitySequence) {
        CommunityEntity entity = repository.findById(communitySequence)
                .orElseThrow(() -> new CommunityNotFound());
        repository.deleteById(communitySequence);
    }

}
