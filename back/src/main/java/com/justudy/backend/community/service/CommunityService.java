package com.justudy.backend.community.service;

import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.response.CommunityResponse;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.community.repository.LoveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {
    private final CommunityRepository communityRepository;

    private final LoveRepository loveRepository;
    private final int MAX_PAGE_SIZE = 10;
// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public Long createCommunity(CommunityCreate request) {
        CommunityEntity community = request.toEntity();
        return communityRepository.save(community).getSequence();
    }

    @Transactional
    public CommunityResponse readCommunity(Long communitySequence) {
        CommunityEntity entity = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        Integer loveCount = loveRepository.readLoveCountByCommunity(communitySequence);
        addViewCount(entity);
        return CommunityResponse.makeBuilder(entity, loveCount);
    }

    public List<CommunityResponse> readAllCommunity(int page,String category) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);

        return communityRepository.findAll(pageable,category)
                .stream()
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long UpdateCommunity(long id, CommunityEdit request) {
        CommunityEntity entity = communityRepository.findById(id)
                .orElseThrow(CommunityNotFound::new);

        entity.update(request.getTitle(), request.getContent(), request.getViewCount(), request.getModifiedTime());
        return id;
    }

    @Transactional
    public void deleteCommunity(Long communitySequence) {
        communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        communityRepository.deleteById(communitySequence);
    }

    public List<CommunityResponse> readAllNoticeCommunity(int page) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        return communityRepository.findAllByNotice(pageable)
                .stream()
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional
    //조회수 증가
    public void addViewCount(CommunityEntity entity) {
        int temp = entity.getViewCount();
        entity.changeViewCount(temp + 1);
    }

    public List<CommunityResponse> search(int page, String type, String search) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        String name = null;
        String title = null;
        String content = null;

        if (type.compareTo("name") == 0) {
            name = search;
        } else if (type.compareTo("title") == 0) {
            title = search;
        } else {
            content = search;
        }

        return communityRepository.findAllBySearchOption(pageable, name, title, content)
                .stream()
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }


    private class CommunityNotFound extends IllegalArgumentException {

    }

    private class CommunityBookmarkNotFound extends IllegalArgumentException {

    }

    private class CommunityBookmarkAlreadyCreated extends IllegalArgumentException {

    }

    private class CommunityLoveAlreadyCreated extends IllegalArgumentException {

    }
}
