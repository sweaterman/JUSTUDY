package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityCommentEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunityCommentCreate;
import com.justudy.backend.community.dto.request.CommunityCommentEdit;
import com.justudy.backend.community.dto.response.CommunityCommentResponse;
import com.justudy.backend.community.exception.CommentNotFound;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.repository.CommunityCommentRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.report.dto.request.CommentReportReason;
import com.justudy.backend.report.dto.response.CommentReportDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityCommentService {
    private final CommunityCommentRepository repository;

    private final CommunityRepository communityRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CommunityCommentResponse createComment(long id, CommunityCommentCreate request) {
        //community 탐색 후 널이면 에러
        CommunityEntity communityEntity = communityRepository.findById(id)
                .orElseThrow(CommunityNotFound::new);
        MemberEntity memberEntity = memberRepository.findById(request.getMemberSeq())
                .orElseThrow(InvalidRequest::new);

        //최대 그룹 찾기
        Integer commentGroup = repository.findByGroup(request.getCommunitySeq());


        //댓글 작성
        if (request.getParentSeq() == null || request.getParentSeq() == 0L) {
            CommunityCommentEntity savedComment = repository.save(CommunityCommentEntity.builder()
                    .member(memberEntity)
                    .community(communityEntity)
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
            return CommunityCommentResponse.makeBuilder(savedComment, request.getMemberSeq());
//            return CommunityCommentResponse.makeBuilder(repository.save(request.toEntity(communityEntity, memberEntity, commentGroup + 1, 0, 0L, 0)));

        } else {//대댓글 작성
            //댓글이 없음 에러
            CommunityCommentEntity parentComment = repository.findById(request.getParentSeq())
                    .orElseThrow(CommentNotFound::new);

            Integer orderResult = orderAndUpdate(parentComment);

            //null 이면 대댓글 작성 오류
            if (orderResult == null)
                return null;
            //대댓글 저장
            CommunityCommentEntity savedComment = repository.save(CommunityCommentEntity.builder()
                    .member(memberEntity)
                    .community(communityEntity)
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
//            CommunityCommentEntity savedComment = repository.save(request.toEntity(communityEntity, memberEntity, parentComment.getGroup(), orderResult, request.getParentSeq(), parentComment.getStep()));

            //부모의 자식 수 업데이트
            repository.updateChildNumber(parentComment.getSequence(), parentComment.getChildNumber());
            return CommunityCommentResponse.makeBuilder(savedComment, request.getMemberSeq());
        }
    }

    @Transactional
    private Integer orderAndUpdate(CommunityCommentEntity parentComment) {
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
    public CommunityCommentResponse readComment(Long id, Long loginSequence) {
        return CommunityCommentResponse.makeBuilder(repository.findById(id).orElseThrow(CommentNotFound::new), loginSequence);
    }

    @Transactional
    public void UpdateComment(long id, long commentId, CommunityCommentEdit request, Long loginSequence) {
        CommunityCommentEntity communityCommentEntity = repository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        if (loginSequence != communityCommentEntity.getMember().getSequence()) throw new InvalidRequest();
        communityCommentEntity.update(request.getContent());
//        repository.save(request.toEntity(commentId));
    }

    @Transactional
    public void deleteComment(long id, long commentId, Long loginSequence) {
        CommunityCommentEntity communityCommentEntity = repository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        if (loginSequence != communityCommentEntity.getMember().getSequence()) throw new InvalidRequest();
        //isDelete true로 변환
        communityCommentEntity.changeIsDeleted(true);
//        repository.save(entity);
    }

    public List<CommunityCommentResponse> readAllComment(long id, Long loginSequence) {
        return repository.readAllComment(id).stream().map(a -> CommunityCommentResponse.makeBuilder(a, loginSequence)).collect(Collectors.toList());
    }

    public CommentReportDetail getCommentReportDetail(Long commentSequence) {
        CommunityCommentEntity comment = repository.findById(commentSequence)
                .orElseThrow(CommentNotFound::new);
        Map<String, String> reasonMap = Arrays.stream(CommentReportReason.class.getEnumConstants())
                .collect(Collectors.toMap(reason -> reason.getValue(), reason -> reason.getMessage()));

        return new CommentReportDetail(comment.getMember().getNickname(), comment.getContent(), reasonMap);
    }

}
