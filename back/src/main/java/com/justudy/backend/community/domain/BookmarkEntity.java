package com.justudy.backend.community.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "community_bookmark")
@Entity
public class BookmarkEntity implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(referencedColumnName = "community_seq", name = "community_seq"),
            @JoinColumn(referencedColumnName = "member_seq", name = "member_seq")
    })
    private CommunityEntity community;

    @Column
    private String memberSeq;
    @Column
    private Boolean isChecked;
    @Column
    private LocalDateTime createdTime;
}
