package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunitySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommunityRepositoryCustom {

    List<CommunityEntity> getAllList(CommunitySearch communitySearch);

    Long getCountOfList(CommunitySearch communitySearch);

    List<CommunityEntity> getAllNotice(Pageable pageable);

    Long getCountOfNotices();

    List<CommunityEntity> getMostLoveListOfWeek(Pageable pageable);

    Optional<CommunityEntity> findBySequence(Long sequence);

    List<CommunityEntity> getListBySequences(List<Long> sequences);

    void updateWeekLoveCount();





    Page<CommunityEntity> findAllByNotice(Pageable pageable);

    Page<CommunityEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content);

    Page<CommunityEntity> findAll(Pageable pageable,String category);

    Long noticeCount();

    List<CommunityEntity> findPopularCommunity();
}
