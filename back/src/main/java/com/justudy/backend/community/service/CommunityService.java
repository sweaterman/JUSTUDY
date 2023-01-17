package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityLove;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.response.CommunityResponse;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.repository.CommunityRepository;
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
    private final CommunityRepository repository;
    private final int MAX_PAGE_SIZE = 10;

    @Transactional
    public Long createCommunity(CommunityCreate request) {
        CommunityEntity community = request.toEntity();
        return repository.save(community).getSequence();
    }

    @Transactional
    public CommunityResponse readCommunity(Long communitySequence) {
        CommunityEntity entity = repository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);

        addViewCount(entity);
        return CommunityResponse.makeBuilder(entity);
    }

    public List<CommunityResponse> readAllCommunity(int page) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);

        return repository.findAll(pageable)
                .stream()
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long UpdateCommunity(long id, CommunityEdit request) {
        CommunityEntity entity = repository.findById(id)
                .orElseThrow(CommunityNotFound::new);

        entity.update(request.getTitle(), request.getContent(), request.getViewCount(), request.getModifiedTime());
        return id;
    }

    @Transactional
    public void deleteCommunity(Long communitySequence) {
        repository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        repository.deleteById(communitySequence);
    }

    public List<CommunityResponse> readAllNoticeCommunity(int page) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        return repository.findAllByNotice(pageable)
                .stream()
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional
    //조회수 증가
    public void addViewCount(CommunityEntity entity) {
        int temp = entity.getViewCount();
        entity.changeViewCount(temp+ 1);
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

        return repository.findAllBySearchOption(pageable, name, title, content)
                .stream()
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public long createLove(CommunityCreate request) {
//        CommunityLove love = repository.findByPK(request);
        return repository.saveLove(request);
    }

    public Long deleteAllLoveByCommunity(Long id) {
        return repository.deleteAllLoveByCommunity(id);
    }

    public Long updateLove(CommunityEdit request) {
        boolean flag = repository.readLove(request);
        return repository.updateLove(request,flag);
    }

    public List<CommunityLove> readAllLoveByCommunity(Long id) {
        return repository.readAllLoveByCommunity(id);
    }

//    public CommunityResponse createBookmark(CommunityCreate request) {
//    }

    private class CommunityNotFound extends IllegalArgumentException {

    }
}
