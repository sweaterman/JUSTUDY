package com.justudy.backend.GroupCall.service;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import com.justudy.backend.GroupCall.repository.StudyRoomRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.repository.StudyRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StudyRoomService {

  private StudyRepository studyRepository;
  private StudyRoomRepository studyRoomRepository;

  @Transactional
  public void saveStudyRoom(Long studySeq) {
    String uuid = UUID.randomUUID().toString();
    studyRoomRepository.save(StudyRoomEntity
        .builder()
        .studyRoomUUID(uuid)
        .studyEntity(studyRepository.getReferenceById(studySeq))
        .build());
  }

  @Transactional
  public String getUUIDStudy(Long studySeq) {
    StudyEntity study = studyRepository.getReferenceById(studySeq);
    return studyRoomRepository.findUUIDByStudy(study);
  }


}
