package com.justudy.backend.timer.domain;

import com.justudy.backend.GroupCall.domain.StudyRoomEntity;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "room_activity")
@Entity
public class RoomActivityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "room_activity_seq")
  private Long sequence;

  @OneToOne
  @JoinColumn(name = "study_room_seq")//추후 추가 예정
  private StudyRoomEntity studyRoom;

  @Column(name = "room_activity_date")
  private Date date;
  @Column(name = "room_activity_time")
  private Long time;
  @Column(name = "room_activity_category")
  private String category;

  @Builder
  public RoomActivityEntity(Long sequence, StudyRoomEntity studyRoom, Date date, Long time,
      String category) {
    this.sequence = sequence;
    this.studyRoom = studyRoom;
    this.date = date;
    this.time = time;
    this.category = category;
  }
}
