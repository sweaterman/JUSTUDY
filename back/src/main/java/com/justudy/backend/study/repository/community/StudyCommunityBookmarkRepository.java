package com.justudy.backend.study.repository.community;

import com.justudy.backend.study.domain.community.StudyCommunityBookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyCommunityBookmarkRepository extends JpaRepository<StudyCommunityBookmarkEntity,Long>, StudyCommunityBookmarkRepositoryCustom {

}
