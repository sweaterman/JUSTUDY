package com.justudy.backend.study.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Table(name = "study_frequency")
@Entity
public class StudyFrequencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "study_frequency_seq")
    private Long sequence;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_seq")
    private StudyEntity study;
    @Column(name = "study_frequency_week")
    private String week;
    @Temporal(TemporalType.TIME)
    @Column(name = "study_frequency_start_time")
    private Date startTime;
    @Temporal(TemporalType.TIME)
    @Column(name = "study_frequency_end_time")
    private Date endTime;
    @Column(name = "study_frequency_created_time")
    private LocalDateTime createdTime;
    @Column(name = "study_frequency_modified_time")
    private LocalDateTime modifiedTime;

    public void update(StudyEntity study, String week, Date startTime, Date endTime) {
        this.study = study;
        this.week = week;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
