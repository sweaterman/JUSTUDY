package com.justudy.backend.chat.domain;


import com.justudy.backend.common.enum_util.Level;
import com.justudy.backend.common.enum_util.Region;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.exception.ForbiddenRequest;
import com.justudy.backend.file.domain.UploadFileEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chat")
@Entity
@Builder
@AllArgsConstructor
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_seq")
    private Long sequence;

    @Column(name = "chat_from")
    private Long from;

    @Column(name = "chat_to")
    private Long to;

    @Column(name = "chat_room")
    private Long room;

    @Column(name = "chat_content")
    private String content;

    @Column(name = "chat_is_read")
    private Boolean isRead;

    @Column(name = "chat_from_time")
    private LocalDateTime fromTime;

    @Column(name = "chat_read_time")
    private LocalDateTime readTime;

}


