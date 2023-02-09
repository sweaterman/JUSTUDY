package com.justudy.backend.study.repository.community;

import com.justudy.backend.study.domain.community.StudyCommunityLoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyCommunityLoveRepository extends JpaRepository<StudyCommunityLoveEntity,Long>, StudyCommunityLoveRepositoryCustom {

}
