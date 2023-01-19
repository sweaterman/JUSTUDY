package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.LoveEntity;
import com.justudy.backend.community.dto.request.BookmarkCreate;
import com.justudy.backend.community.dto.request.LoveCreate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoveRepositorySupport {

    Long saveLove(LoveCreate request);

    Long updateLove(LoveCreate request, boolean flag);

    List<LoveEntity> readAllLoveByCommunity(Long id);

    Long deleteAllLoveByCommunity(Long id);

    Boolean readLove(LoveCreate request);

    Integer readLoveCountByCommunity(Long communitySequence);
}
