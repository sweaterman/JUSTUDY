package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.CommunityLoveEntity;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.exception.LoveNotFound;
import com.justudy.backend.community.repository.CommunityLoveRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommunityLoveService {
    private final CommunityLoveRepository loveRepository;

    private final CommunityRepository communityRepository;

    @Transactional
    public Long createLove(Long loginSequence, Long communitySequence) {
        CommunityEntity community = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        CommunityLoveEntity savedLove = loveRepository.findLove(loginSequence, communitySequence)
                .orElseGet(() ->
                        loveRepository.save(makeNewLove(loginSequence, communitySequence, community))
                );
        return savedLove.getSequence();
    }

    @Transactional
    public void deleteLove(Long loginSequence, Long communitySequence) {
        CommunityEntity community = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        CommunityLoveEntity findLove = loveRepository.findLove(loginSequence, communitySequence)
                .orElseThrow(LoveNotFound::new);
        community.removeWeekLoveCount();
        loveRepository.delete(findLove);
    }

    @Transactional
    public void deleteAllByCommunity(Long communitySequence) {
        loveRepository.deleteAllByCommunity(communitySequence);
    }

    public Integer getCountOfLove(Long communitySequence) {
        return loveRepository.countOfLove(communitySequence);
    }

    public List<Long> getMyLoves(Long loginSequence) {
        return loveRepository.findCommunitySequence(loginSequence);
    }

    public Optional<CommunityLoveEntity> findLove(Long loginSequence, Long communitySequence) {
        return loveRepository.findLove(loginSequence, communitySequence);
    }

    private CommunityLoveEntity makeNewLove(Long loginSequence, Long communitySequence, CommunityEntity community) {
        community.addWeekLoveCount();
        return new CommunityLoveEntity(loginSequence, communitySequence);
    }
}
