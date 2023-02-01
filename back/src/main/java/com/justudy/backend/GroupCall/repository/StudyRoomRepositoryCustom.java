package com.justudy.backend.GroupCall.repository;

import com.justudy.backend.GroupCall.dto.response.StudyRoomResponse;
import com.justudy.backend.study.domain.StudyEntity;

public interface StudyRoomRepositoryCustom {

  StudyRoomResponse findUUIDByStudy(StudyEntity study);
}
