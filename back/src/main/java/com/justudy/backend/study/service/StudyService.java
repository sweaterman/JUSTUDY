package com.justudy.backend.study.service;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyEdit;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {

    private final StudyRepository studyRepository;
    private final int MAX_STUDY_PAGE_SIZE = 10;
    private final int MAX_NOTICE_SIZE = 3;

    //crud readall search readopen readopencategory readsubcategory myapplystudy myapplyCRUD mystudyall
    // studycommunityCRUD studycommunitycommentCRUD studyfrequencyCRUD
    // study alarm king average attendencemonth attendenceday memberCRUD studyapplyCRUD
// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public Long createStudy(StudyCreate request) {
        StudyEntity study = request.toEntity();
        return studyRepository.save(study).getSequence();
    }

    @Transactional
    public StudyResponse readStudy(Long studySequence) {
        StudyEntity entity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        return StudyResponse.makeBuilder(entity);
    }

    @Transactional
    public Long updateStudy(long id, StudyEdit request) {
        StudyEntity entity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);

        entity.update(request.getName(), request.getIntroduction());
        return id;
    }

    @Transactional
    public void deleteStudy(Long studySequence) {
        studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        studyRepository.deleteById(studySequence);
    }

    @Transactional
    public List<StudyResponse> readAllCommunity(int page, String category) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        //맨 처음페이지는 공지3개 추가
        //공지 없을 시 일반글로 출력
        Long noticeCount = studyRepository.noticeCount();
        if (page == 0 && noticeCount > 0) {
            noticeCount = noticeCount < MAX_NOTICE_SIZE ? noticeCount : MAX_NOTICE_SIZE;
            Pageable noticePageable = PageRequest.of(0, noticeCount.intValue());
            Pageable categoryPageable = PageRequest.of(0, MAX_PAGE_SIZE - noticeCount.intValue());

            List<StudyResponse> noticeList = studyRepository.findAllByNotice(noticePageable)
                    .stream()
                    .map(StudyResponse::makeBuilder)
                    .collect(Collectors.toList());
            List<StudyResponse> categoryList = studyRepository.findAll(categoryPageable, category)
                    .stream()
                    .map(StudyResponse::makeBuilder)
                    .collect(Collectors.toList());

            noticeList.addAll(categoryList);
            return noticeList;
        }

        return studyRepository.findAll(pageable, category)
                .stream()
                .map(StudyResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<StudyResponse> readAllNoticeCommunity(int page) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        return studyRepository.findAllByNotice(pageable)
                .stream()
                .map(StudyResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional
    //조회수 증가
    public void addViewCount(StudyEntity entity) {
        int temp = entity.getViewCount();
        entity.changeViewCount(temp + 1);
    }

    public List<StudyResponse> search(int page, String type, String search) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        String name = null;
        String title = null;
        String content = null;

        if (type.compareTo("name") == 0) {
            name = search;
        } else if (type.compareTo("title") == 0) {
            title = search;
        } else {
            content = search;
        }

        return studyRepository.findAllBySearchOption(pageable, name, title, content)
                .stream()
                .map(StudyResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<StudyResponse> readPopularCommunity() {
        return studyRepository.findPopularCommunity()
                .stream()
                .map(StudyResponse::makeBuilder)
                .collect(Collectors.toList());
    }

}
