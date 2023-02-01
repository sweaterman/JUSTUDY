package com.justudy.backend.GroupCall.repository;

import com.justudy.backend.study.domain.StudyEntity;

public interface StudyRoomRepositoryCustom {
  String findUUIDByStudy(StudyEntity study);
}
