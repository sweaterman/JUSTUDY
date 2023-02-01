package com.justudy.backend.rank.domain;

import com.justudy.backend.rank.common.RankGroup;
import com.justudy.backend.rank.common.RankType;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "ranking")
public class RankEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "rank_seq")
  private Long sequence;
  @Column(name = "rank_group")
  @Enumerated(EnumType.STRING)
  private RankGroup rankGroup;
  @Column(name = "rank_type")
  @Enumerated(EnumType.STRING)
  private RankType rankType;

  @Column(name = "rank_order")
  private Integer rankOrder;
  @Column(name = "rank_name")
  private String rankName;
  @Column(name = "rank_time")
  private Long rankTime;
  @Column(name = "rank_image")
  private Long rankImage;

  @Builder
  public RankEntity(Long sequence, RankGroup rankGroup, RankType rankType, Integer rankOrder,
      String rankName, Long rankTime,Long rankImage) {
    this.sequence = sequence;
    this.rankGroup = rankGroup;
    this.rankType = rankType;
    this.rankOrder = rankOrder;
    this.rankName = rankName;
    this.rankTime = rankTime;
    this.rankImage = rankImage;
  }

}
