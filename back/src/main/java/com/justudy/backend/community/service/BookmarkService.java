package com.justudy.backend.community.service;

import com.justudy.backend.community.domain.BookmarkEntity;
import com.justudy.backend.community.dto.request.BookmarkCreate;
import com.justudy.backend.community.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {
    private final BookmarkRepository repository;

    public void createBookmark(BookmarkCreate request) {
        //북마크가 생성되어있다면 isChecked true && createdTime now()
        BookmarkEntity entity = repository.readBookmark(request);
        if(entity==null) {
            repository.saveBookmark(request);
            return;
        }
        repository.updateBookmark(request);
    }

    public void deleteBookmark(Long id,Long userId) {
        //북마크가 없다면 error
//        BookmarkEntity bookmarkEntity = repository.readBookmark(request);
//        throw new CommunityBookmarkNotFound();

        repository.deleteBookmark(id,userId);
    }

    public List<BookmarkEntity> readAllBookmarkByMember(Long userId) {
        return repository.readAllBookmarkByMember(userId);
    }

}
