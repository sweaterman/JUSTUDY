//package com.justudy.backend.community.dto.response;
//
//import com.justudy.backend.community.domain.CommunityEntity;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Getter
//@NoArgsConstructor
//public class CommunityLoveResponse {
//    private Long sequence;
//    private Long memberSeq;
//    private LocalDateTime createdTime;
//    private Integer loveCount;
//
//    @Builder
//    public CommunityLoveResponse(Long sequence, Long memberSeq, Long category_seq, String title, String content, Integer viewCount, LocalDateTime createdTime, LocalDateTime modifiedTime, Integer loveCount) {
//        this.sequence = sequence;
//        this.memberSeq = memberSeq;
//        this.category_seq = category_seq;
//        this.title = title;
//        this.content = content;
//        this.viewCount = viewCount;
//        this.createdTime = createdTime;
//        this.modifiedTime = modifiedTime;
//        this.loveCount = loveCount;
//    }
//    public static CommunityLoveResponse makeBuilder(CommunityEntity entity) {
//        return makeBuilder(entity,0);
//    }
//    public static CommunityLoveResponse makeBuilder(CommunityEntity entity, Integer loveCount) {
//        return CommunityLoveResponse.builder()
//                .sequence(entity.getSequence())
//                .member_seq(entity.getMember_seq())
//                .category_seq(entity.getCategory_seq())
//                .title(entity.getTitle())
//                .content(entity.getContent())
//                .viewCount(entity.getViewCount())
//                .createdTime(entity.getCreatedTime())
//                .modifiedTime(entity.getModifiedTime())
//                .loveCount(loveCount)
//                .build();
//    }
//}
