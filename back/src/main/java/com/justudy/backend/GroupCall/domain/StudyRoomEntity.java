package com.justudy.backend.GroupCall.domain;

import com.justudy.backend.study.domain.StudyEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "study_room")
@Entity
@Builder
public class StudyRoomEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "room_seq")
  private Long sequence;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "study_seq")
  private StudyEntity studyEntity;

  @Column(name = "study_room_uuid")
  private String studyRoomUUID;

  public StudyRoomEntity(Long sequence, StudyEntity studyEntity, String studyRoomUUID) {
    this.sequence = sequence;
    this.studyEntity = studyEntity;
    this.studyRoomUUID = studyRoomUUID;
  }
}
