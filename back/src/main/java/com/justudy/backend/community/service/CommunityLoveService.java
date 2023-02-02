package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityLoveEntity;
import com.justudy.backend.community.repository.CommunityLoveRepository;
import com.justudy.backend.exception.InvalidRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommunityLoveService {
    private final CommunityLoveRepository loveRepository;

    @Transactional
    public Long createLove(Long loginSequence, Long communitySequence) {
        CommunityLoveEntity savedLove = loveRepository.findLove(loginSequence, communitySequence)
                .orElseGet(() ->
                        loveRepository.save(makeNewLove(loginSequence, communitySequence))
                );
        return savedLove.getSequence();
    }

    @Transactional
    public void deleteLove(Long loginSequence, Long communitySequence) {
        CommunityLoveEntity findLove = loveRepository.findLove(loginSequence, communitySequence)
                .orElseThrow(() -> new InvalidRequest());

        loveRepository.delete(findLove);
    }

    @Transactional
    public void deleteAllByCommunity(Long communitySequence) {
        loveRepository.deleteAllByCommunity(communitySequence);
    }

    private CommunityLoveEntity makeNewLove(Long loginSequence, Long communitySequence) {
        return new CommunityLoveEntity(loginSequence, communitySequence);
    }
}
