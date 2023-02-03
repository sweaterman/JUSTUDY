package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityLoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityLoveRepository extends JpaRepository<CommunityLoveEntity,Long>, CommunityLoveRepositoryCustom {

}
