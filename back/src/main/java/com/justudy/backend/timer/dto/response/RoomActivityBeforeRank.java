package com.justudy.backend.timer.dto.response;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import lombok.Data;

@Data
public class RoomActivityBeforeRank {

  private StudyRoomEntity studyRoom;
  private Long second;


  public RoomActivityBeforeRank(StudyRoomEntity studyRoom, Long second) {
    this.studyRoom = studyRoom;
    this.second = second;
  }


}
