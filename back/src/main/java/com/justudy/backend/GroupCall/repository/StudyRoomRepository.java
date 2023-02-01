package com.justudy.backend.GroupCall.repository;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoomEntity, Long>,
    StudyRoomRepositoryCustom {

}
