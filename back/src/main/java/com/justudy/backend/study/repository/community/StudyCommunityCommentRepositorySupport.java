package com.justudy.backend.study.repository.community;



import com.justudy.backend.study.domain.community.StudyCommunityCommentEntity;

import java.util.List;

public interface StudyCommunityCommentRepositorySupport {
    List<StudyCommunityCommentEntity> readAllComment(long id);

    Integer findByGroup(Long communitySeq);

    void updateChildNumber(Long sequence, Integer childNumber);

    Integer findByChildNumberSum(Integer group);

    Integer findByMaxStep(Integer group);

    void updateOrderPlus(Integer group, int i);
}
