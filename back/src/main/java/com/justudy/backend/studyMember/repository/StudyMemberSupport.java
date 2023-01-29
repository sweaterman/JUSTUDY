package com.justudy.backend.studyMember.repository;

import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudyMemberSupport {


    Page<StudyMemberEntity> findAllByNotice(Pageable pageable);

    Page<StudyMemberEntity> findAllBySearchOption(Pageable pageable, String name, String title, String content);

    Page<StudyMemberEntity> findAll(Pageable pageable, String category);

    Long noticeCount();

    List<StudyMemberEntity> findPopularCommunity();
}
