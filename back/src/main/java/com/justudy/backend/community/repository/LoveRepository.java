package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.domain.LoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoveRepository extends JpaRepository<LoveEntity,Long>,LoveRepositorySupport{

}
