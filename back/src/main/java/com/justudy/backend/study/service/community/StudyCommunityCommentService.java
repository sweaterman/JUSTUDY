package com.justudy.backend.study.service.community;

import com.justudy.backend.community.exception.CommentNotFound;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.exception.ForbiddenRequest;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.community.StudyCommunityCommentEntity;
import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import com.justudy.backend.study.dto.request.community.StudyCommunityCommentCreate;
import com.justudy.backend.study.dto.request.community.StudyCommunityCommentEdit;
import com.justudy.backend.study.dto.response.community.StudyCommunityCommentResponse;
import com.justudy.backend.study.exception.StudyMemberNotFound;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyRepository;
import com.justudy.backend.study.repository.community.StudyCommunityCommentRepository;
import com.justudy.backend.study.repository.community.StudyCommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyCommunityCommentService {
    private final StudyCommunityCommentRepository repository;

    private final StudyCommunityRepository communityRepository;
    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public StudyCommunityCommentResponse createComment(long id, StudyCommunityCommentCreate request, Long studySequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, request.getMemberSeq());

        //community 탐색 후 널이면 에러
        StudyCommunityEntity communityEntity = communityRepository.findById(id)
                .orElseThrow(CommunityNotFound::new);
        MemberEntity memberEntity = memberRepository.findById(request.getMemberSeq())
                .orElseThrow(InvalidRequest::new);

        //최대 그룹 찾기
        Integer commentGroup = repository.findByGroup(request.getCommunitySeq());


        //댓글 작성
        if (request.getParentSeq() == null || request.getParentSeq() == 0L) {
            StudyCommunityCommentEntity savedComment = repository.save(StudyCommunityCommentEntity.builder()
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
            return StudyCommunityCommentResponse.makeBuilder(savedComment, request.getMemberSeq());
        } else {//대댓글 작성
            //댓글이 없음 에러
            StudyCommunityCommentEntity parentComment = repository.findById(request.getParentSeq())
                    .orElseThrow(CommentNotFound::new);
            Integer orderResult = orderAndUpdate(parentComment);
            //대댓글 저장
            StudyCommunityCommentEntity savedComment = repository.save(StudyCommunityCommentEntity.builder()
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
            //부모의 자식 수 업데이트
            repository.updateChildNumber(parentComment.getSequence(), parentComment.getChildNumber());
            return StudyCommunityCommentResponse.makeBuilder(savedComment, request.getMemberSeq());
        }
    }

    @Transactional
    private Integer orderAndUpdate(StudyCommunityCommentEntity parentComment) {
        int step = parentComment.getStep() + 1;
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
        } else {
            repository.updateOrderPlus(group, order);
            return order + 1;
        }
    }

    //한건의 댓글 읽기
    public StudyCommunityCommentResponse readComment(Long id, Long loginSequence) {

        return StudyCommunityCommentResponse.makeBuilder(repository.findById(id).orElseThrow(CommentNotFound::new), loginSequence);
    }

    @Transactional
    public void UpdateComment(long id, long commentId, StudyCommunityCommentEdit request, Long loginSequence, Long studySequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, loginSequence);

        StudyCommunityCommentEntity communityCommentEntity = repository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        if (loginSequence.longValue() != communityCommentEntity.getMember().getSequence().longValue()) throw new InvalidRequest();
        communityCommentEntity.update(request.getContent());
//        repository.save(request.toEntity(commentId));
    }

    @Transactional
    public void deleteComment(long id, long commentId, Long loginSequence, Long studySequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, loginSequence);

        StudyCommunityCommentEntity communityCommentEntity = repository.findById(commentId)
                .orElseThrow(CommentNotFound::new);
        if (loginSequence.longValue() != communityCommentEntity.getMember().getSequence().longValue()) throw new InvalidRequest();
        //isDelete true로 변환
        communityCommentEntity.changeIsDeleted(true);
    }

    public List<StudyCommunityCommentResponse> readAllComment(long id, Long loginSequence, Long studySequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, loginSequence);

        return repository.readAllComment(id).stream().map(a -> StudyCommunityCommentResponse.makeBuilder(a, loginSequence)).collect(Collectors.toList());
    }
    private void isStudyMember(StudyEntity studyEntity, Long sequence) {
        studyEntity.getStudyMembers()
                .stream()
                .map(studyMemberEntity -> studyMemberEntity.getMember().getSequence())
                .filter(memberSequence -> memberSequence.longValue() == sequence.longValue())
                .findFirst()
                .orElseThrow(ForbiddenRequest::new);
    }
}
