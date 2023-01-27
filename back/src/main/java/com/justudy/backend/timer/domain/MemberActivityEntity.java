package com.justudy.backend.timer.domain;

import com.justudy.backend.member.domain.MemberEntity;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_activity")
@Entity
public class MemberActivityEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "member_activity_seq")
  private Long sequence;

  @OneToOne
  @JoinColumn(name ="member_seq")
  private MemberEntity member;

  @Column(name ="member_activity_date")
  private Date date;
  @Column(name ="member_activity_time")
  private Integer time;
  @Column(name ="member_activity_category")
  private String category;
}
