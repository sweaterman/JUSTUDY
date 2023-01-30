package com.justudy.backend.timer.repository;

import com.justudy.backend.timer.domain.RoomActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomActivityRepository extends JpaRepository<RoomActivityEntity, Long>,
    RoomActivityRepositoryCustom {

}
