package com.justudy.backend.community.repository;


import com.justudy.backend.community.domain.CommunityCommentEntity;

import java.util.List;

public interface CommunityCommentRepositorySupport {
    List<CommunityCommentEntity> readAllComment(long id);

    Integer findByGroup(Long communitySeq);

    void updateChildNumber(Long sequence, Integer childNumber);

    Integer findByChildNumberSum(Integer group);

    Integer findByMaxStep(Integer group);

    void updateOrderPlus(Integer group, int i);
}
