package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.dto.request.CommunityBookmarkCreate;
import com.justudy.backend.community.repository.CommunityBookmarkRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityBookmarkService {
    private final CommunityBookmarkRepository repository;

    @Transactional
    public void createBookmark(CommunityBookmarkCreate request) {
        //북마크가 생성되어있다면 isChecked true && createdTime now()
        Optional<CommunityBookmarkEntity> entity = repository.readBookmark(request);
        if(entity.isEmpty()) {
            CommunityBookmarkEntity createEntity = request.toEntity();
            repository.save(createEntity);
            return;
        }
        repository.updateBookmark(request);
    }

    @Transactional
    public void deleteBookmark(Long id,Long userId) {
        //북마크가 없다면 error
//        BookmarkEntity bookmarkEntity = repository.readBookmark(request);
//        throw new CommunityBookmarkNotFound();

        repository.deleteBookmark(id,userId);
    }

    public List<CommunityBookmarkEntity> readAllBookmarkByMember(Long userId) {
        return repository.readAllBookmarkByMember(userId);
    }

}
