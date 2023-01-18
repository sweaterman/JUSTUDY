package com.justudy.backend.comment.service;

import com.justudy.backend.comment.domain.CommentEntity;
import com.justudy.backend.comment.dto.request.CommentCreate;
import com.justudy.backend.comment.dto.request.CommentEdit;
import com.justudy.backend.comment.dto.response.CommentResponse;
import com.justudy.backend.comment.repository.CommentRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository repository;

    private final CommunityRepository communityRepository;

    @Transactional
    public CommentResponse createComment(long id, CommentCreate request) {
        //community 탐색 후 널이면 에러
        communityRepository.findById(id)
                .orElseThrow(CommunityNotFound::new);

        //최대 그룹 찾기
        Integer commentGroup = repository.findByGroup(request.getCommunitySeq());
        //댓글 작성
        if (request.getParentSeq() == null || request.getParentSeq() == 0) {
            CommentEntity savedComment = repository.save(CommentEntity.builder()
                    .memberSeq(request.getMemberSeq())
                    .communitySeq(request.getCommunitySeq())
                    .content(request.getContent())
                    .createdTime(LocalDateTime.now())
                    .modifiedTime(null)
                    .isDeleted(false)
                    .group(commentGroup + 1)
                    .order(0)
                    .parentSeq(0L)
                    .step(0)
                    .childNumber(0)
                    .build());
            return CommentResponse.makeBuilder(savedComment);

        } else {//대댓글 작성
            //댓글이 없음 에러
            CommentEntity parentComment = repository.findById(request.getParentSeq())
                    .orElseThrow(CommentNotFound::new);

            Integer orderResult = orderAndUpdate(parentComment);
            //null 이면 대댓글 작성 오류
            if (orderResult == null)
                return null;
            //대댓글 저장
            CommentEntity savedComment = repository.save(CommentEntity.builder()
                    .memberSeq(request.getMemberSeq())
                    .communitySeq(request.getCommunitySeq())
                    .content(request.getContent())
                    .createdTime(LocalDateTime.now())
                    .modifiedTime(null)
                    .isDeleted(false)
                    .group(parentComment.getGroup())
                    .order(orderResult)
                    .parentSeq(request.getParentSeq())
                    .step(parentComment.getStep() + 1)
                    .childNumber(0)
                    .build());

            //부모의 자식 수 업데이트
            repository.updateChildNumber(parentComment.getSequence(), parentComment.getChildNumber());
            return CommentResponse.makeBuilder(savedComment);
        }
    }

    @Transactional
    private Integer orderAndUpdate(CommentEntity parentComment) {
        Integer step = parentComment.getStep() + 1;
        Integer order = parentComment.getOrder();
        Integer childNumber = parentComment.getChildNumber();
        Integer group = parentComment.getGroup();

        Integer childNumberSum = repository.findByChildNumberSum(group);
        Integer maxStep = repository.findByMaxStep(group);

        if (step < maxStep)
            return childNumberSum + 1;
        else if (step == maxStep) {
            repository.updateOrderPlus(group, order + childNumber);
            return order + childNumber + 1;
        } else if (step > maxStep) {
            repository.updateOrderPlus(group, order);
            return order + 1;
        }
        return null;
    }

    //한건의 댓글 읽기
    public CommentResponse readComment(Long id) {
        return CommentResponse.makeBuilder(repository.findById(id)
                .orElseThrow(CommentNotFound::new));
    }

    @Transactional
    public void UpdateComment(long id, long commentId, CommentEdit request) {
        repository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        repository.save(request.toEntity(commentId));
    }

    @Transactional
    public void deleteComment(long id, long commentId) {
        CommentEntity entity = repository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        //isDelete true로 변환
        entity.changeIsDeleted(true);
//        repository.save(entity);
    }

    public List<CommentResponse> readAllComment(long id) {
        return repository.readAllComment(id).stream().map(CommentResponse::makeBuilder).collect(Collectors.toList());
    }

    private class CommentNotFound extends IllegalArgumentException {

    }

    private class CommunityNotFound extends IllegalArgumentException {

    }
}
