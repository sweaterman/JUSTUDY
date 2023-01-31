package com.justudy.backend.study.service;

import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyEdit;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final int MAX_STUDY_PAGE_SIZE = 1;
    private final int MAX_NOTICE_SIZE = 3;

    //crud readall search readopen readopencategory readsubcategory myapplystudy myapplyCRUD mystudyall
    // studycommunityCRUD studycommunitycommentCRUD studyfrequencyCRUD
    // study alarm king average attendencemonth attendenceday memberCRUD studyapplyCRUD
// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public Long createStudy(StudyCreate request, UploadFileEntity basicImage) {

        StudyEntity study = request.toEntity();
        study.changeImage(basicImage);
        return studyRepository.save(study).getSequence();
    }

    public StudyResponse readStudy(Long studySequence) {
        StudyEntity entity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        return StudyResponse.makeBuilder(entity);
    }

    @Transactional
    public Long updateStudy(long id, StudyEdit request) {
        StudyEntity entity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);

        entity.update(request.getName(), request.getLeaderSeq(), request.getIntroduction(), request.getPersonnel(),
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


    public Slice<StudyResponse> search(int page, List<String> sub, String type, String search) {
        Pageable pageable = PageRequest.of(page, MAX_STUDY_PAGE_SIZE);

        Long leaderSeq = null;
        String studyName = null;

        //todo member 이름으로 검색
        if (type.compareTo("name") == 0) {
//            leaderSeq = memberRepository.(Long.parseLong(search)).get().getSequence();
        } else if (type.compareTo("title") == 0) {
            studyName = search;
        }

        return studyRepository.findAllBySearchOption(pageable, sub, leaderSeq, studyName)
                .map(StudyResponse::makeBuilder);
    }
}
