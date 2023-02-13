package com.justudy.backend.study.service.community;

import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.exception.LoveNotFound;
import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import com.justudy.backend.study.domain.community.StudyCommunityLoveEntity;
import com.justudy.backend.study.repository.community.StudyCommunityLoveRepository;
import com.justudy.backend.study.repository.community.StudyCommunityRepository;
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
public class StudyCommunityLoveService {
    private final StudyCommunityLoveRepository loveRepository;

    private final StudyCommunityRepository communityRepository;

    @Transactional
    public Long createLove(Long loginSequence, Long communitySequence, Long studySequence) {
        StudyCommunityEntity community = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        StudyCommunityLoveEntity savedLove = loveRepository.findLove(loginSequence, communitySequence)
                .orElseGet(() ->
                        loveRepository.save(makeNewLove(loginSequence, communitySequence, community))
                );
        return savedLove.getSequence();
    }

    @Transactional
    public void deleteLove(Long loginSequence, Long communitySequence, Long studySequence) {
        StudyCommunityEntity community = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        StudyCommunityLoveEntity findLove = loveRepository.findLove(loginSequence, communitySequence)
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

    public Optional<StudyCommunityLoveEntity> findLove(Long loginSequence, Long communitySequence) {
        return loveRepository.findLove(loginSequence, communitySequence);
    }

    private StudyCommunityLoveEntity makeNewLove(Long loginSequence, Long communitySequence, StudyCommunityEntity community) {
        community.addWeekLoveCount();
        return new StudyCommunityLoveEntity(loginSequence, communitySequence);
    }
}
