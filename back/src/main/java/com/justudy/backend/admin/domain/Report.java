package com.justudy.backend.admin.domain;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Report {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_seq")
    private Long sequence;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reporter_seq")
    private MemberEntity reporter;

    @Column(name = "target_seq")
    private Long targetSequence;

    @Column(name = "report_created_time")
    private LocalDateTime createdTime;

    @Column(name = "report_is_finished")
    private boolean isFinished;

    @Column(name = "report_finished_time")
    private LocalDateTime finishedTime;

    public Report(MemberEntity reporter, Long targetSequence,
                  LocalDateTime createdTime,
                  boolean isFinished, LocalDateTime finishedTime) {
        this.reporter = reporter;
        this.targetSequence = targetSequence;
        this.createdTime = createdTime;
        this.isFinished = isFinished;
        this.finishedTime = finishedTime;
    }
}
