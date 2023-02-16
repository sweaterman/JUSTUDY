package com.justudy.backend.timer.service;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import com.justudy.backend.GroupCall.dto.response.StudyRoomResponse;
import com.justudy.backend.GroupCall.repository.StudyRoomRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyRepository;
import com.justudy.backend.timer.domain.QRoomActivityEntity;
import com.justudy.backend.timer.domain.RoomActivityEntity;
import com.justudy.backend.timer.dto.request.ActivityRequest;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.ActivityToRank;
import com.justudy.backend.timer.dto.response.RoomActivityBeforeRank;
import com.justudy.backend.timer.repository.RoomActivityRepository;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoomActivityService {

  private final StudyRepository studyRepository;

  private final QRoomActivityEntity qRoomActivityEntity = QRoomActivityEntity.roomActivityEntity;
  private final RoomActivityRepository roomActivityRepository;
  private final StudyRoomRepository studyRoomRepository;

  @Transactional
  public void saveRoomActivity(ActivityRequest roomActivityRequest, Long roomSequence, Date today) {

    StudyRoomEntity studyRoom = studyRoomRepository.getReferenceById(roomSequence);
    roomActivityRepository.save(RoomActivityEntity
        .builder()
        .studyRoom(studyRoom)
        .date(today)
        .time(roomActivityRequest.getSecond())
        .category(roomActivityRequest.getCategory())
        .build());
  }

  //수정 예정 : 무언가 꼬였음..
  @Transactional
  public List<ActivityCalendarResponse> getCalendarTimeById(Date ago, Date cur, Long studySeq) {
    StudyEntity study = studyRepository.getReferenceById(studySeq);
    if (study == null) {
      throw new StudyNotFound();
    }
    StudyRoomResponse response = studyRoomRepository.findUUIDByStudy(study);
    if (response == null) {
      throw new StudyNotFound();
    }
    StudyRoomEntity studyRoom = studyRoomRepository.getReferenceById(response.getSequence());
    if (studyRoom == null) {
      throw new StudyNotFound();
    }

    return roomActivityRepository.findCalendarById(ago, cur, studyRoom);

  }


  @Transactional
  public List<ActivityToRank> getSumTimeByPeriod(Date ago, Date before) {
    List<RoomActivityBeforeRank> rabrList = roomActivityRepository.sumTimeByPeriod(ago, before);

    List<ActivityToRank> ret = IntStream.range(0, rabrList.size())
        .mapToObj(index -> dataConvert(index, rabrList.get(index))).collect(
            Collectors.toList());

    return ret;
  }

  private ActivityToRank dataConvert(int index, RoomActivityBeforeRank roomActivityBeforeRank) {
    StudyEntity studyEntity = roomActivityBeforeRank.getStudyRoom().getStudyEntity();
    Long image = studyEntity.getImageFile().getSequence();
    Long seq = studyEntity.getSequence();
    return new ActivityToRank(index + 1, roomActivityBeforeRank.getSecond(), studyEntity.getName(),
        image, seq);
  }


  @Transactional
  public void deleteRoomActivity(Long studySequence) {
    StudyEntity study = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
    StudyRoomEntity studyRoom = studyRoomRepository.getReferenceById(
        study.getStudyRoom().getSequence());
    List<RoomActivityEntity> roomActivityEntities = roomActivityRepository.findByStudyRoom(
        studyRoom.getSequence());
    roomActivityRepository.deleteAllInBatch(roomActivityEntities);
  }

  @Transactional
  public List<ActivitySubjectResponse> getAllTimeByCategory() {
    return roomActivityRepository.sumAllTimeByCategory();
  }
}
