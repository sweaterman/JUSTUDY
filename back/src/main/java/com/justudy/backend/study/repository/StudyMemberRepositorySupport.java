package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.StudyMemberEntity;

import java.util.List;

public interface StudyMemberRepositorySupport {

    void deleteStudyMember(Long id, Long memberId);

    List<StudyMemberEntity> readAllRegisterStudy(Long id);

    void deleteStudyMemberByStudy(Long studySequence);
}
