package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityCommentEntity,Long>, CommunityCommentRepositorySupport {

}
