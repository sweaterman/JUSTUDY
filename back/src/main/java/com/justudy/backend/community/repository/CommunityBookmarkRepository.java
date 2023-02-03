package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityBookmarkRepository extends JpaRepository<CommunityBookmarkEntity,Long>, CommunityBookmarkRepositoryCustom {

}
