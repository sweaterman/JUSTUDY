package com.justudy.backend.study.domain;

import com.justudy.backend.studyMember.domain.StudyMemberEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Table(name = "study_community")
@Entity
public class StudyCommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "study_community_seq")
    private Long sequence;
}
