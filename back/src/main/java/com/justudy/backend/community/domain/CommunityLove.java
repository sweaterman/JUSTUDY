package com.justudy.backend.community.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "community_love")
@Entity
public class CommunityLove implements Serializable {
    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(referencedColumnName = "community_seq", name = "community_seq"),
            @JoinColumn(referencedColumnName = "member_seq",name = "member_seq")
    })
    private CommunityEntity community;

    @Column
    private Boolean isChecked;
}
