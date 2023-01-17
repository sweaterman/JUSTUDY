package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.CommunityLove;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityRepositorySupport {


    Page<CommunityEntity> findAllByNotice(Pageable pageable);

    Page<CommunityEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content);

    Page<CommunityEntity> findAll(Pageable pageable);


    long saveLove(CommunityCreate request);

    Long updateLove(CommunityEdit request,boolean flag);

    List<CommunityLove> readAllLoveByCommunity(Long id);

    Long deleteAllLoveByCommunity(Long id);

    boolean readLove(CommunityEdit request);
}
