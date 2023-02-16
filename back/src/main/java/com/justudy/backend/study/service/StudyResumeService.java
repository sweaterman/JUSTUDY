package com.justudy.backend.study.service;

import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.exception.MemberNotFound;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.study.dto.request.StudyResumeCreate;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.dto.response.StudyResumeResponse;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.exception.StudyResumeNotFound;
import com.justudy.backend.study.repository.StudyRepository;
import com.justudy.backend.study.repository.StudyResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyResumeService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final StudyResumeRepository studyResumeRepository;

    @Transactional
    public Long createStudyResume(StudyResumeCreate request) {
        StudyEntity studyEntity = studyRepository.findById(request.getStudySeq())
                .orElseThrow(StudyNotFound::new);
        MemberEntity memberEntity = memberRepository.findById(request.getMemberSeq())
                .orElseThrow(MemberNotFound::new);

        StudyResumeEntity studyResumeEntity = studyResumeRepository.save(request.toEntity(studyEntity, memberEntity));
        studyEntity.addStudyResume(studyResumeEntity);
        return studyResumeEntity.getSequence();
    }

    @Transactional
    public void deleteStudyResume(Long id, Long loginSequence) {
        StudyEntity studyEntity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);

        StudyResumeEntity studyResumeEntity = studyEntity.getResumes()
                .stream()
                .filter(memberEntity -> memberEntity.getMember().getSequence().longValue() == loginSequence.longValue())
                .findFirst()
                .orElseThrow(StudyResumeNotFound::new);

        if (studyResumeEntity.getMember().getSequence().longValue() != loginSequence.longValue())
            throw new InvalidRequest();
        studyEntity.removeStudyResume(studyResumeEntity);
        studyResumeRepository.deleteById(studyResumeEntity.getSequence());
    }

    public StudyResumeResponse readStudyResume(Long resumeSeq) {
        StudyResumeEntity entity = studyResumeRepository.findById(resumeSeq)
                .orElseThrow(StudyResumeNotFound::new);
        return StudyResumeResponse.makeBuilder(entity);
    }


    public List<StudyResumeResponse> readAllStudyResumeByStudy(Long id, Long loginSequence) {
        StudyEntity studyEntity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);
        if (loginSequence.longValue() != studyEntity.getLeaderSeq().longValue()) throw new InvalidRequest();

        return studyResumeRepository.readAllStudyResumeByStudy(id)
                .stream()
                .map(StudyResumeResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<StudyResponse> readAllApplyStudy(Long id) {
        return studyResumeRepository.readAllStudyResumeByMember(id)
                .stream()
                .map(StudyResumeEntity::getStudy)
                .map(StudyResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id, Long resumeSeq) {
        StudyEntity studyEntity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);
        StudyResumeEntity studyResumeEntity = studyResumeRepository.findById(resumeSeq)
                .orElseThrow(StudyResumeNotFound::new);
        studyEntity.removeStudyResume(studyResumeEntity);
        studyResumeRepository.deleteById(resumeSeq);
    }

    @Transactional
    public void deleteStudyResumeByStudy(Long id) {
        StudyEntity studyEntity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);

        //ConcurrentModificationException을 피하기위해 iterator를 활용한 제거
//        for (Iterator<StudyResumeEntity> iterator = studyEntity.getResumes().iterator(); iterator.hasNext(); ) {
//            iterator.next().changeStudy(null);
//            iterator.remove();
//        }
        studyResumeRepository.deleteStudyResumeByStudy(id);
    }
}


