package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.CommunityLoveEntity;
import com.justudy.backend.community.dto.request.CommunityLoveCreate;
import com.justudy.backend.community.exception.CommunityLoveAlreadyCreated;
import com.justudy.backend.community.exception.CommunityLoveNotFound;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.repository.CommunityLoveRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityLoveService {
    private final CommunityLoveRepository repository;
    private final CommunityRepository communityRepository;

    // ---------------------------------------------------------------좋아요---------------------------------------------------------------
    @Transactional
    public long createLove(CommunityLoveCreate request) {
        Optional<CommunityLoveEntity> love = repository.readLove(request);
        if (love.isEmpty()) {
            CommunityLoveEntity entity = request.toEntity();
            repository.save(entity);
            Optional<CommunityEntity> community = communityRepository.findById(request.getCommunity().getSequence());
            if (community.isEmpty())
                throw new CommunityNotFound();

            addWeekLoveCount(community.get());
            return 1;
        }
        throw new CommunityLoveAlreadyCreated();
    }

    public List<CommunityLoveEntity> readAllLoveByCommunity(Long id) {
        return repository.readAllLoveByCommunity(id);
    }

    @Transactional
    public Long deleteAllLoveByCommunity(Long id) {
        return repository.deleteAllLoveByCommunity(id);
    }

    @Transactional
    public Long updateLove(CommunityLoveCreate request) {
        Optional<CommunityLoveEntity> entity = repository.readLove(request);
        if (entity.isEmpty())
            throw new CommunityLoveNotFound();
        return repository.updateLove(request, entity.get().getIsChecked());
    }

    @Transactional
    //조회수 증가
    public void addWeekLoveCount(CommunityEntity entity) {
        int temp = entity.getWeekLoveCount();
        entity.changeWeekLoveCount(temp + 1);
    }
}
