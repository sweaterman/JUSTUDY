package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityRepositoryCustom {


    Page<CommunityEntity> findAllByNotice(Pageable pageable);

    Page<CommunityEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content);

    Page<CommunityEntity> findAll(Pageable pageable,String category);

    Long noticeCount();

    List<CommunityEntity> findPopularCommunity();
}
