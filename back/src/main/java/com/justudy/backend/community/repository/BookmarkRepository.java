package com.justudy.backend.community.repository;

import com.justudy.backend.community.domain.BookmarkEntity;
import com.justudy.backend.community.domain.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity,Long>,BookmarkRepositorySupport{

}
