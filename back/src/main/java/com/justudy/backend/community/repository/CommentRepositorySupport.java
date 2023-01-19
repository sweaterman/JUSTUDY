package com.justudy.backend.community.repository;

import com.justudy.backend.comment.domain.CommentEntity;

import java.util.List;

public interface CommentRepositorySupport {
    List<CommentEntity> readAllComment(long id);

    Integer findByGroup(Long communitySeq);

    void updateChildNumber(Long sequence, Integer childNumber);

    Integer findByChildNumberSum(Integer group);

    Integer findByMaxStep(Integer group);

    void updateOrderPlus(Integer group, int i);
}
