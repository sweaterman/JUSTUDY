package com.justudy.backend.timer.domain;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "room_activity")
@Entity
public class RoomActivityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "room_activity_seq")
  private Long sequence;

//  @Column(name ="study_room_seq")//추후 추가 예정
//  private Long studyRoomSeq;

  @Column(name = "room_activity_date")
  private Date date;
  @Column(name = "room_activity_time")
  private Integer time;
  @Column(name = "room_activity_category")
  private String category;

}
