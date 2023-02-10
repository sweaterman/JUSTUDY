package com.justudy.backend.study.repository.community;

import com.justudy.backend.study.domain.community.StudyCommunityCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyCommunityCommentRepository extends JpaRepository<StudyCommunityCommentEntity,Long>, StudyCommunityCommentRepositorySupport {

}
