package com.justudy.backend.study.repository;

import com.justudy.backend.study.domain.StudyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMemberEntity, Long>, StudyMemberRepositorySupport {

}
