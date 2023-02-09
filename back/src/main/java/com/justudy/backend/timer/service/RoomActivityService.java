package com.justudy.backend.timer.service;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import com.justudy.backend.GroupCall.dto.response.StudyRoomResponse;
import com.justudy.backend.GroupCall.repository.StudyRoomRepository;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.repository.StudyRepository;
import com.justudy.backend.timer.domain.QRoomActivityEntity;
import com.justudy.backend.timer.domain.RoomActivityEntity;
import com.justudy.backend.timer.dto.request.ActivityRequest;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivityToRank;
import com.justudy.backend.timer.dto.response.RoomActivityBeforeRank;
import com.justudy.backend.timer.repository.RoomActivityRepository;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
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
  public void saveRoomAcitivity(ActivityRequest roomActivityRequest, Long roomSequence, Date today) {

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
      return null;//에러 페이지 넣기
    }
    log.info("getCalendarTimeById {} {} {} {} {}",study.getSequence(),study.getCreatedTime(),study.getCategory(),study.getModifiedTime(),study.getName());
    StudyRoomResponse response = studyRoomRepository.findUUIDByStudy(study);
    if (response == null) {
      return null;//에러 페이지 넣기
    }
    StudyRoomEntity studyRoom = studyRoomRepository.getReferenceById(response.getSequence());

    if (studyRoom == null) {
      return null;//에러 페이지 넣기
    }

    return roomActivityRepository.findCalendarById(ago, cur, studyRoom);

  }


  @Transactional
  public List<ActivityToRank> getSumTimeByPeriod(Date ago, Date before) {
    List<RoomActivityBeforeRank> rabrList = roomActivityRepository.sumTimeByPeriod(ago, before);

    List<ActivityToRank> ret = new LinkedList<ActivityToRank>();

    for (int index = 0; index < rabrList.size(); index++) {
      RoomActivityBeforeRank rabr = rabrList.get(index);
      StudyEntity studyEntity = rabr.getStudyRoom().getStudyEntity();
      Long image = studyEntity.getImageFile().getSequence();
      Integer order = index + 1;
      String name = studyEntity.getName();
      Long sumTime = rabr.getSecond();

      ret.add(new ActivityToRank(order, sumTime, name, image));
    }
    return ret;
  }

}
