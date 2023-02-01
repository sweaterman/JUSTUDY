package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudyMemberRepositorySupport {

    void deleteStudyMember(Long id, Long memberId);

    List<StudyMemberEntity> readAllRegisterStudy(Long id);

    void deleteStudyMemberByStudy(Long studySequence);
}
