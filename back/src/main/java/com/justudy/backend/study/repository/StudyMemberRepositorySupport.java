package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.StudyMemberEntity;

import java.util.List;
import java.util.Optional;

public interface StudyMemberRepositorySupport {

    void deleteStudyMember(Long id, Long memberId);

    List<StudyMemberEntity> readAllRegisterStudy(Long id);

    void deleteStudyMemberByStudy(Long studySequence);

   Optional<StudyMemberEntity> readStudyMemberById(Long id, Long memberId);
}
