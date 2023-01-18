package com.justudy.backend.comment.repository;

import com.justudy.backend.comment.domain.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommentRepositorySupport {


    Page<CommentEntity> findAllByNotice(Pageable pageable);

    Page<CommentEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content);

    Page<CommentEntity> findAll(Pageable pageable);

    Long saveComment(Long id, CommentEntity comment);

    Optional<CommentEntity> readComment(Long id);


    List<CommentEntity> readAllComment(long id);

    Integer findByGroup(Long communitySeq);

    void updateChildNumber(Long sequence, Integer childNumber);

    Integer findByChildNumberSum(Integer group);

    Integer findByMaxStep(Integer group);

    void updateOrderPlus(Integer group, int i);
}
