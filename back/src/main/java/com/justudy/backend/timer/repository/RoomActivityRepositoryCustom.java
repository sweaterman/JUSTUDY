package com.justudy.backend.timer.repository;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.timer.domain.RoomActivityEntity;
import com.justudy.backend.timer.dto.response.ActivityCalendarResponse;
import com.justudy.backend.timer.dto.response.ActivitySubjectResponse;
import com.justudy.backend.timer.dto.response.RoomActivityBeforeRank;
import java.sql.Date;
import java.util.List;

public interface RoomActivityRepositoryCustom {

  List<ActivityCalendarResponse> findCalendarById(Date ago, Date cur, StudyRoomEntity studyRoom);

  List<RoomActivityBeforeRank> sumTimeByPeriod(Date ago, Date cur);

  List<RoomActivityEntity> findByStudyRoom(Long roomSeq);

  List<ActivitySubjectResponse> sumAllTimeByCategory();
}
