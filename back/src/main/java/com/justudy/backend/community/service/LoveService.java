package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.BookmarkEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.LoveEntity;
import com.justudy.backend.community.dto.request.BookmarkCreate;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.request.LoveCreate;
import com.justudy.backend.community.dto.response.CommunityResponse;
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
public class LoveService {
    private final LoveRepository repository;
// ---------------------------------------------------------------좋아요---------------------------------------------------------------

    public long createLove(LoveCreate request) {
        Boolean love = repository.readLove(request);
        if (love == null || !love) {
            throw new CommunityLoveAlreadyCreated();
        }
        return repository.saveLove(request);
    }

    public List<LoveEntity> readAllLoveByCommunity(Long id) {
        return repository.readAllLoveByCommunity(id);
    }

    public Long deleteAllLoveByCommunity(Long id) {
        return repository.deleteAllLoveByCommunity(id);
    }

    public Long updateLove(LoveCreate request) {
        boolean flag = repository.readLove(request);
        return repository.updateLove(request, flag);
    }

    private class CommunityLoveAlreadyCreated extends IllegalArgumentException {

    }
}
