package com.justudy.backend.comment.repository;

import com.justudy.backend.comment.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long>, CommentRepositorySupport {

}
