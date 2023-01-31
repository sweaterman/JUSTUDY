package com.justudy.backend.study.service;

import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import com.justudy.backend.study.dto.request.StudyMemberCreate;
import com.justudy.backend.study.dto.request.StudyMemberEdit;
import com.justudy.backend.study.dto.response.StudyMemberResponse;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.exception.StudyMemberNotFound;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyMemberRepository;
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
public class StudyMemberService {

    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final int MAX_STUDY_PAGE_SIZE = 10;
    private final int MAX_NOTICE_SIZE = 3;

    @Transactional
    public void updateStudyLeader(Long id, Long memberId) {
        StudyEntity entity = studyRepository.findById(id)
                .orElseThrow(StudyNotFound::new);
        StudyMemberEntity studyMemberEntity = studyMemberRepository.
                entity.update(request.getName(), request.getLeaderSeq(), request.getIntroduction(), request.getPersonnel(),
                        request.getLevel(), request.getOnlineOffline(), request.getIsOpen(), request.getGithub(),
                        request.getNotion(), request.getStartTime());
        return id;
    }
    public List<StudyResponse> readAllRegisterStudy(Long id) {
        //todo move to studymember find
        StudyMemberEntity studyMemberEntity = studyMemberRepository.readAllRegisterStudy(id);
        return studyRepository.readAllRegisterStudy(memberEntity);
    }

}
