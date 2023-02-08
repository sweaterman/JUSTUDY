package com.justudy.backend.chat.dto.response;

import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import com.justudy.backend.study.dto.response.StudyFrequencyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
@AllArgsConstructor
@Builder
public class ChatResponse {
    private Long sequence;
    private Long from;
    private Long to;
    private Long room;
    private String content;
    private Boolean isRead;
    private LocalDateTime fromTime;
    private LocalDateTime readTime;

    public static ChatResponse makeBuilder(StudyEntity entity) {
        //todo builder make
        log.info("슬라이드5 {}",entity.getStudyMembers().size());

        return ChatResponse.builder()
                .build();
    }

}
