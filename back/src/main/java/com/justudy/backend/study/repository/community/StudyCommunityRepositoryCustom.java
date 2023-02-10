package com.justudy.backend.study.repository.community;

import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudyCommunityRepositoryCustom {

    List<StudyCommunityEntity> getAllList(CommunitySearch communitySearch, Long studySequence);

    Long getCountOfList(CommunitySearch communitySearch, Long studySequence);

    List<StudyCommunityEntity> getAllNotice(Pageable pageable);

    Long getCountOfNotices();

    List<StudyCommunityEntity> getMostLoveListOfWeek(Pageable pageable);

    Optional<StudyCommunityEntity> findBySequence(Long sequence);

    List<StudyCommunityEntity> getListBySequences(List<Long> sequences);





    Page<StudyCommunityEntity> findAllByNotice(Pageable pageable);

    Page<StudyCommunityEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content);

    Long noticeCount();

    List<StudyCommunityEntity> findPopularCommunity();
}
