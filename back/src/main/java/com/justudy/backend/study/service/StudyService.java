package com.justudy.backend.study.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.service.FileStore;
import com.justudy.backend.file.service.UploadFileService;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.member.service.MemberService;
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
    private final UploadFileService uploadFileService;

    private final FileStore fileStore;

// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public Long createStudy(StudyCreate request, UploadFileEntity basicImage) {

        CategoryEntity categoryEntity = categoryRepository.findByKey(request.getBottomCategory()).orElseThrow(InvalidRequest::new);

        StudyEntity study = request.toEntity(categoryEntity);

        log.info("llooo1{}",basicImage.getSequence());
        study.changeImage(basicImage);
        log.info("llooo2{}",basicImage.getStoreFileName());
        log.info("llooo2{}",basicImage.getUploadFileName());
        return studyRepository.save(study).getSequence();
    }

    public StudyDetailResponse readStudy(Long studySequence) {
        StudyEntity entity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        String leaderName = memberRepository.findById(entity.getLeaderSeq()).get().getNickname();
        return StudyDetailResponse.makeBuilder(entity, leaderName);
    }

    public StudyDetailResponse readDetailStudy(Long studySequence, Long loginSequence) {
        StudyEntity entity = studyRepository.findById(studySequence)
                .orElseThrow(StudyNotFound::new);
        Boolean isLeader = false;
        Boolean isMember = false;
        Boolean isApply = false;


        //맴버일시
        Long seq = entity.getStudyMembers()
                .stream()
                .map(studyMemberEntity -> studyMemberEntity.getMember().getSequence())
                .filter(sequence -> sequence.longValue() == loginSequence)
                .findFirst()
                .orElse(null);
        if (seq != null)
            isMember = true;

        //가입신청햇을시
        if (studyResumeRepository.readStudyResumeByStudyAndMember(entity.getSequence(), loginSequence).orElse(null) != null)
            isApply = true;


        //리더일시
        String leaderName = memberRepository.findById(loginSequence).get().getNickname();
        if (entity.getLeaderSeq() == loginSequence) {
            isLeader = true;
            isMember = false;
        }
        return StudyDetailResponse.makeBuilder(entity, isApply, isMember, isLeader, leaderName);
    }

    @Transactional
    public Long updateStudy(long id, StudyEdit request, UploadFileEntity uploadImage) {
        StudyEntity entity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);

        entity.changeImage(uploadImage);

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


    public StudySearchResponse search(Pageable pageable, List<String> sub, String type, String search) {
        int MAX_STUDY_PAGE_SIZE = 9;
//        Pageable pageable = PageRequest.of(page, MAX_STUDY_PAGE_SIZE);

        String studyLeader = null;
        String studyName = null;

        if (type != null && type.compareTo("leader") == 0) {
            studyLeader = search;
        } else if (type != null && type.compareTo("name") == 0) {
            studyName = search;
        }
        log.info("서치 : {} {}", type, search);
        Slice<StudyEntity> studyEntities = studyRepository.findAllBySearchOption(pageable, sub, studyLeader, studyName);
        return StudySearchResponse.makeBuilder(studyEntities
                .stream()
                .map(StudyResponse::makeBuilder)
                .collect(Collectors.toList()), studyEntities.hasNext());
    }


    public StudyEntity getStudyByLeader(Long leaderSeq) {
        return studyRepository.findByLeaderSeq(leaderSeq);
    }

    public Boolean checkNickName(String nickName) {
        if (studyRepository.readStudyByNickName(nickName) == null) return true;
        return false;
    }

    public Long getStudyLeader(Long id) {
        return studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new).getLeaderSeq();
    }
}
