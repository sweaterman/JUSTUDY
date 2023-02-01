package com.justudy.backend.GroupCall.dto.response;

import com.justudy.backend.study.domain.StudyEntity;
import lombok.Data;

@Data
public class StudyRoomResponse {

  private String uuid;
  private StudyEntity study;

  public StudyRoomResponse(String uuid, StudyEntity study) {
    this.uuid = uuid;
    this.study = study;
  }
}
