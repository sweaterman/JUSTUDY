package com.justudy.backend.report.domain;

import com.justudy.backend.member.domain.MemberEntity;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"reporter"})
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

    @Column(name = "report_content")
    private String content;

    @Column(name = "report_created_time")
    private LocalDateTime createdTime;

    @Column(name = "report_is_finished")
    private boolean isFinished;

    @Column(name = "report_finished_time")
    private LocalDateTime finishedTime;

    public Report(MemberEntity reporter, Long targetSequence, String content) {
        this.reporter = reporter;
        this.targetSequence = targetSequence;
        this.content = content;
        this.createdTime = LocalDateTime.now();
        this.isFinished = false;
        this.finishedTime = null;
    }

    public Long acceptReport() {
        this.isFinished = true;
        this.finishedTime = LocalDateTime.now();
        return this.sequence;
    }
}
