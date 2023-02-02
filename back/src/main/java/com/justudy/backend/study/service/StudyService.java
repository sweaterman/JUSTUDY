package com.justudy.backend.study.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.exception.InvalidRequest;
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
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final StudyResumeRepository studyResumeRepository;
    private final StudyFrequencyRepository studyFrequencyRepository;

// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public Long createStudy(StudyCreate request, UploadFileEntity basicImage) {

        CategoryEntity categoryEntity = categoryRepository.findByKey(request.getBottomCategory()).orElseThrow(InvalidRequest::new);
//        memberRepository.find
        //todo leaderSeq 멤버에서 검색해야함
        StudyEntity study = request.toEntity(categoryEntity);

//        log.info("슬라이스3 info : {}", study.getStudyMembers().size());
        study.changeImage(basicImage);
        return studyRepository.save(study).getSequence();
    }

    public StudyDetailResponse readStudy(Long studySequence) {
        StudyEntity entity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        return StudyDetailResponse.makeBuilder(entity);
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
        int MAX_STUDY_PAGE_SIZE = 9;
        Pageable pageable = PageRequest.of(page, MAX_STUDY_PAGE_SIZE);

        String studyLeader = null;
        String studyName = null;

        //todo 스터디장 스터디명 검색
        if (type != null && type.compareTo("leader") == 0) {
            studyLeader = search;
        } else if (type != null && type.compareTo("name") == 0) {
            studyName = search;
        }
        Slice<StudyEntity> studyEntities = studyRepository.findAllBySearchOption(pageable, sub, studyLeader, studyName);
        return StudySearchResponse.makeBuilder(studyEntities
                .stream()
                .map(StudyResponse::makeBuilder)
                .collect(Collectors.toList()), studyEntities.isLast());
    }


    public StudyEntity getStudyByLeader(Long leaderSeq) {
        return studyRepository.findByLeaderSeq(leaderSeq);
    }
}
