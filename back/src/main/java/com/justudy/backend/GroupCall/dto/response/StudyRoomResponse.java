package com.justudy.backend.GroupCall.dto.response;

import com.justudy.backend.study.domain.StudyEntity;
import lombok.Data;

@Data
public class StudyRoomResponse {

  private String uuid;
  private Long sequence;

  public StudyRoomResponse(String uuid, Long sequence) {
    this.uuid = uuid;
    this.sequence = sequence;
  }
}
