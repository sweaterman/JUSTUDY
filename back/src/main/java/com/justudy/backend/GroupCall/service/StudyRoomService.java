package com.justudy.backend.GroupCall.service;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import com.justudy.backend.GroupCall.dto.response.StudyRoomResponse;
import com.justudy.backend.GroupCall.repository.StudyRoomRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.repository.StudyRepository;
import java.util.UUID;

import com.justudy.backend.timer.repository.RoomActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StudyRoomService {

  private final StudyRepository studyRepository;
  private final StudyRoomRepository studyRoomRepository;
  private final RoomActivityRepository roomActivityRepository;

  @Transactional
  public void saveStudyRoom(Long studySeq) {
    String uuid = UUID.randomUUID().toString();
    StudyEntity study = studyRepository.getReferenceById(studySeq);

    study.changeStudyRoom(studyRoomRepository.save(StudyRoomEntity
            .builder()
            .studyRoomUUID(uuid)
            .studyEntity(study)
            .build()));
  }


  @Transactional
  public StudyRoomResponse getUUIDStudy(Long studySeq) {
    StudyEntity study = studyRepository.getReferenceById(studySeq);
    if(study == null){
      return null;
    }
    return studyRoomRepository.findUUIDByStudy(study);

  }


    public void deleteStudyRoom(Long studySeq) {
        StudyEntity study = studyRepository.getReferenceById(studySeq);

        //활동기록 삭제
        studyRoomRepository.deleteById(study.getStudyRoom().getSequence());
        study.changeStudyRoom(null);
    }
}
