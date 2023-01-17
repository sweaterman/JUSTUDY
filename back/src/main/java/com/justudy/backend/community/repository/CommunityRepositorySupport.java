package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommunityRepositorySupport {


    Page<CommunityEntity> findAllByNotice(Pageable pageable);

    Page<CommunityEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content);

    Page<CommunityEntity> findAll(Pageable pageable);
}
