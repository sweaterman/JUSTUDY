package com.justudy.backend.studyMember.service;

import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import com.justudy.backend.studyMember.dto.request.StudyMemberCreate;
import com.justudy.backend.studyMember.dto.request.StudyMemberEdit;
import com.justudy.backend.studyMember.dto.response.StudyMemberResponse;
import com.justudy.backend.studyMember.exception.StudyMemberNotFound;
import com.justudy.backend.studyMember.repository.StudyMemberMember;
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
public class StudyMemberService {

    private final StudyMemberMember studyMemberRepository;
    private final int MAX_STUDY_PAGE_SIZE = 10;
    private final int MAX_NOTICE_SIZE = 3;
    //crud readall search readopen readopencategory readsubcategory myapplystudy myapplyCRUD mystudyall
    // studycommunityCRUD studycommunitycommentCRUD studyfrequencyCRUD
    // study alarm king average attendencemonth attendenceday memberCRUD studyapplyCRUD
    // ---------------------------------------------------------------커뮤니티---------------------------------------------------------------
    @Transactional
    public StudyMemberResponse readCommunity(Long communitySequence) {
//        StudyMemberEntity entity = studyRepository.findById(communitySequence)
//                .orElseThrow(StudyNotFound::new);
//        Integer loveCount = communityLoveRepository.readLoveCountByCommunity(communitySequence);
//        addViewCount(entity);
//        return StudyMemberResponse.makeBuilder(entity, loveCount);

        return null;
    }

    @Transactional
    public Long createCommunity(StudyMemberCreate request) {
        StudyMemberEntity community = request.toEntity();
        return studyMemberRepository.save(community).getSequence();
    }


    @Transactional
    public List<StudyMemberResponse> readAllCommunity(int page, String category) {
        Pageable pageable = PageRequest.of(page, MAX_STUDY_PAGE_SIZE);
        //맨 처음페이지는 공지3개 추가
        //공지 없을 시 일반글로 출력
        Long noticeCount = studyMemberRepository.noticeCount();
        if (page == 0 && noticeCount > 0) {
            noticeCount = noticeCount < MAX_NOTICE_SIZE ? noticeCount : MAX_NOTICE_SIZE;
            Pageable noticePageable = PageRequest.of(0, noticeCount.intValue());
            Pageable categoryPageable = PageRequest.of(0, MAX_STUDY_PAGE_SIZE - noticeCount.intValue());

            List<StudyMemberResponse> noticeList = studyMemberRepository.findAllByNotice(noticePageable)
                    .stream()
                    .map(StudyMemberResponse::makeBuilder)
                    .collect(Collectors.toList());
            List<StudyMemberResponse> categoryList = studyMemberRepository.findAll(categoryPageable, category)
                    .stream()
                    .map(StudyMemberResponse::makeBuilder)
                    .collect(Collectors.toList());

            noticeList.addAll(categoryList);
            return noticeList;
        }

        return studyMemberRepository.findAll(pageable, category)
                .stream()
                .map(StudyMemberResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateCommunity(long id, StudyMemberEdit request) {
//        StudyMemberEntity entity = studyRepository.findById(id)
//                .orElseThrow(StudyNotFound::new);
//
//        entity.update(request.getTitle(), request.getContent(), request.getViewCount(), request.getModifiedTime());
//        return id;
        return null;
    }

    @Transactional
    public void deleteCommunity(Long communitySequence) {
        studyMemberRepository.findById(communitySequence)
                .orElseThrow(StudyMemberNotFound::new);
        studyMemberRepository.deleteById(communitySequence);
    }

    public List<StudyMemberResponse> readAllNoticeCommunity(int page) {
        Pageable pageable = PageRequest.of(page, MAX_STUDY_PAGE_SIZE);
        return studyMemberRepository.findAllByNotice(pageable)
                .stream()
                .map(StudyMemberResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    @Transactional
    //조회수 증가
    public void addViewCount(StudyMemberEntity entity) {
//        int temp = entity.getViewCount();
//        entity.changeViewCount(temp + 1);
    }

    public List<StudyMemberResponse> search(int page, String type, String search) {
        Pageable pageable = PageRequest.of(page, MAX_STUDY_PAGE_SIZE);
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

        return studyMemberRepository.findAllBySearchOption(pageable, name, title, content)
                .stream()
                .map(StudyMemberResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<StudyMemberResponse> readPopularCommunity() {
        return studyMemberRepository.findPopularCommunity()
                .stream()
                .map(StudyMemberResponse::makeBuilder)
                .collect(Collectors.toList());
    }

}
