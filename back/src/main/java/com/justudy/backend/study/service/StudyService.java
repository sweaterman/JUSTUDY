package com.justudy.backend.study.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.community.exception.InvalidRequest;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyEdit;
import com.justudy.backend.study.dto.response.StudyDetailResponse;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.dto.response.StudySearchResponse;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyFrequencyRepository;
import com.justudy.backend.study.repository.StudyRepository;
import com.justudy.backend.study.repository.StudyResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final StudyResumeRepository studyResumeRepository;
    private final StudyFrequencyRepository studyFrequencyRepository;
    private final int MAX_STUDY_PAGE_SIZE = 1;

    //crud readall search readopen readopencategory readsubcategory myapplystudy myapplyCRUD mystudyall
    // studycommunityCRUD studycommunitycommentCRUD studyfrequencyCRUD
    // study alarm king average attendencemonth attendenceday memberCRUD studyapplyCRUD
// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public Long createStudy(StudyCreate request, UploadFileEntity basicImage) {
        CategoryEntity categoryEntity = categoryRepository.findByName(request.getBottomCategory()).orElseThrow(InvalidRequest::new);
//        memberRepository.find
        //todo leaderSeq 멤버에서 검색해야함
        Long leaderSeq=1L;
        StudyEntity study = request.toEntity(categoryEntity, leaderSeq);
        study.changeImage(basicImage);
        return studyRepository.save(study).getSequence();
    }

    public StudyResponse readStudy(Long studySequence) {
        StudyEntity entity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        return StudyResponse.makeBuilder(entity);
    }

    public StudyDetailResponse readDetailStudy(Long studySequence) {
        StudyEntity entity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        return StudyDetailResponse.makeBuilder(entity);
    }

    @Transactional
    public Long updateStudy(long id, StudyEdit request) {
        StudyEntity entity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);
        //이전 활동주기 삭제
        studyFrequencyRepository.deleteByStudy(id);
        //활동주기 생성
        studyFrequencyRepository.saveAll(request.getFrequency()
                .stream()
                .map(studyFrequencyCreate -> studyFrequencyCreate.toEntity(entity))
                .collect(Collectors.toList()));

        entity.update(request.getName(), request.getIntroduction(), request.getPopulation(),
                request.getLevel(), request.getOnlineOffline(), request.getIsOpen(), request.getGithub(),
                request.getNotion(), request.getStartTime());
        return id;
    }

    @Transactional
    public void deleteStudy(Long studySequence) {
        studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        studyRepository.deleteById(studySequence);
    }


    public StudySearchResponse search(int page, List<String> sub, String type, String search) {
        Pageable pageable = PageRequest.of(page, MAX_STUDY_PAGE_SIZE);

        String studyLeader = null;
        String studyName = null;

        //todo 스터디장 스터디명 검색
        if (type.compareTo("스터디장") == 0) {
            studyLeader = search;
        } else if (type.compareTo("스터디명") == 0) {
            studyName = search;
        }
        Slice<StudyResponse> studyResponses = studyRepository.findAllBySearchOption(pageable, sub, studyLeader, studyName)
                .map(StudyResponse::makeBuilder);
        return StudySearchResponse.makeBuilder(studyResponses.stream().collect(Collectors.toList()), studyResponses.isLast());
    }


}
