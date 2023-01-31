package com.justudy.backend.studyMember.repository;

import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMemberMember extends JpaRepository<StudyMemberEntity,Long>, StudyMemberSupport {

}
