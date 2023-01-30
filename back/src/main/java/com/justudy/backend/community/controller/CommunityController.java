package com.justudy.backend.community.controller;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.community.domain.CommunityBookmarkEntity;
import com.justudy.backend.community.domain.CommunityLoveEntity;
import com.justudy.backend.community.dto.request.*;
import com.justudy.backend.community.dto.response.CommunityCommentResponse;
import com.justudy.backend.community.dto.response.CommunityResponse;
import com.justudy.backend.community.service.CommunityBookmarkService;
import com.justudy.backend.community.service.CommunityCommentService;
import com.justudy.backend.community.service.CommunityService;
import com.justudy.backend.community.service.CommunityLoveService;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {

    private final MemberService memberService;
    private final CommunityService communityService;
    private final CommunityCommentService communityCommentService;
    private final CommunityLoveService communityLoveService;
    private final CommunityBookmarkService communityBookmarkService;
    private final CategoryService categoryService;
    // ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    /**
     * 모든 검색 정보를 가져오는 API
     *
     * @param page   페이지 넘버
     * @param type   검색 분류(아이디, 제목, 내용)
     * @param search 검색할 값
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
     */
    @GetMapping("/board/search")
    public ResponseEntity<List<CommunityResponse>> readAllCommunityBySearch(@RequestParam("page") int page, @RequestParam("type") String type, @RequestParam("saerch") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.search(page, type, search));
    }

    /**
     * 일주일간 좋아요 많은 커뮤니티 정보를 가져오는 API
     *
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
     */
    @GetMapping("/board/love")
    public ResponseEntity<List<CommunityResponse>> readPopularCommunity() {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.readPopularCommunity());
    }

    /**
     * 카테고리별 커뮤니티 정보를 가져오는 API
     *
     * @param page 페이지 넘버
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 1페이지는 맨위 N개 공지 정보 + 커뮤니티 정보 목록
     */
    @GetMapping("/board")
    public ResponseEntity<List<CommunityResponse>> readAllCommunity(@RequestParam("page") int page,@RequestParam("category") String category) {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.readAllCommunity(page,category));
    }

    /**
     * 커뮤니티 공지 정보를 가져오는 API
     *
     * @param page 페이지 넘버
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 공지 정보 목록
     */
    @GetMapping("/board/notice")
    public ResponseEntity<List<CommunityResponse>> readAllNoticeCommunity(@RequestParam("page") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.readAllNoticeCommunity(page));
    }

    /**
     * 커뮤니티 상세 정보를 가져오는 API
     *
     * @param id 커뮤니티 sequence (PK)
     * @return ResponseEntity<CommunityResponse> 200 OK, 커뮤니티 상세 정보
     */
    @GetMapping("/board/{id}")
    public ResponseEntity<CommunityResponse> readCommunityById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.readCommunity(id));
    }

    /**
     * 커뮤니티 생성 API
     *
     * @param request 생성 정보
     * @return ResponseEntity<CommunityResponse> 201 Created, 생성된 커뮤니티 정보
     */
    @PostMapping("/board")
    public ResponseEntity<CommunityResponse> createCommunity(@RequestBody CommunityCreate request, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        MemberEntity findMember = memberService.getMember(loginSequence);

        CategoryEntity category = categoryService.getCategory(request.getCategory());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(communityService.readCommunity(communityService.createCommunity(request, findMember, category)));
    }

    /**
     * 커뮤니티 수정 API
     *
     * @param communitySequence      커뮤니티 sequence (PK)
     * @param request 수정 정보
     * @return ResponseEntity<UserResponse> 200 OK, 수정된 커뮤니티글 정보
     */
    @PutMapping("/board/{id}")
    public ResponseEntity<CommunityResponse> updateCommunity(@PathVariable("id") Long communitySequence, @RequestBody CommunityEdit request, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        communityService.updateCommunity(communitySequence, request);

        return ResponseEntity.status(HttpStatus.OK).body(communityService.readCommunity());
    }

    /**
     * 커뮤니티 삭제 API
     *
     * @param communitySequence 커뮤니티 sequence (PK)
     * @return ResponseEntity<Void> 204 No Content
     */
    @DeleteMapping("/board/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable("id") long communitySequence, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        communityService.deleteCommunity(loginSequence, communitySequence);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

//    /**
//     * 모든 커뮤니티 정보를 가져오는 API
//     *
//     * @param page 페이지 넘버
//     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
//     */
//    @GetMapping("/board")
//    public ResponseEntity<List<CommunityResponse>> readAllCommunity(@RequestParam("page") int page) {
//        return ResponseEntity.status(HttpStatus.OK).body(communityService.readAllCommunity(page));
//    }

    // ---------------------------------------------------------------북마크---------------------------------------------------------------

    /**
     * 북마크 생성 API
     *
     * @param id 커뮤니티 sequence (PK)
     * @return ResponseEntity<Void>> 201 CREATED
     */
    @PostMapping("/board/{id}/bookmark")
    public ResponseEntity<Void> createBookmark(@PathVariable("id") long id, @RequestBody CommunityBookmarkCreate request) {
        communityBookmarkService.createBookmark(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    /**
     * 북마크 삭제 API
     *
     * @param id 커뮤니티 sequence (PK)
     * @return ResponseEntity<List < CommunityResponse>> 204 NO_CONTENT'
     * 북마크는 유저기능아닌가? 매핑 바꿔야할듯
     * 복합키로 delete 어캐구현하지
     */
    @DeleteMapping("/board/{id}/bookmark/{userId}")
    public ResponseEntity<List<CommunityResponse>> deleteBookmark(@PathVariable("id") Long id,@PathVariable("userId") Long userId) {
        communityBookmarkService.deleteBookmark(id,userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 북마크 불러오기 API
     *
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 유저별 북마크 목록
     */
    @GetMapping("/board/bookmark/{userId}")
    public ResponseEntity<List<CommunityBookmarkEntity>> readAllBookmarkByMember(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(communityBookmarkService.readAllBookmarkByMember(userId));
    }
    // ---------------------------------------------------------------좋아요---------------------------------------------------------------

    /**
     * 좋아요 생성 API
     *
     * @param id 커뮤니티 sequence
     * @return ResponseEntity<Void> 좋아요 처음누를시 201 CREATED 이미 눌렀을 시 409 CONFLICT
     */
    @PostMapping("/board/{id}/love")
    public ResponseEntity<Void> createLove(@PathVariable("id") long id, CommunityLoveCreate request) {
        if (communityLoveService.createLove(request) == 1) return ResponseEntity.status(HttpStatus.CREATED).body(null);
        else//create 실패
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    /**
     * 좋아요 수정 API
     *
     * @param id 커뮤니티 sequence
     * @return ResponseEntity<Void> 200 OK
     * 일반 유저가 사용 x
     */
    @PutMapping("/board/{id}/love")
    public ResponseEntity<List<CommunityResponse>> updateBookmark(@PathVariable("id") long id, CommunityLoveCreate request) {
        if (communityLoveService.updateLove(request) == 1) return ResponseEntity.status(HttpStatus.OK).body(null);
        else//update 실패
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    /**
     * 좋아요 삭제 API
     *
     * @param id     커뮤니티 sequence
     * @param loveId 좋아요 sequence
     * @return ResponseEntity<Void> 204 NO_CONTENT
     * 게시글 삭제시 모든 좋아요 삭제
     */
    @DeleteMapping("/board/{id}/love/{loveId}")
    public ResponseEntity<Void> deleteLove(@PathVariable("id") Long id, @PathVariable("loveId") Long loveId) {
        if (communityLoveService.deleteAllLoveByCommunity(loveId) == 1)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else//update 실패
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    /**
     * 좋아요 불러오기 API
     *
     * @param id     커뮤니티 sequence
     * @param loveId 좋아요 sequence
     * @return ResponseEntity<List < CommunityLove>> 200 OK, 좋아요 정보 목록
     * 관리전용
     */
    @GetMapping("/board/love/{id}")
    public ResponseEntity<List<CommunityLoveEntity>> readLove(@PathVariable("id") Long id, @PathVariable("id") Long loveId) {
        return ResponseEntity.status(HttpStatus.OK).body(communityLoveService.readAllLoveByCommunity(loveId));
    }


// ---------------------------------------------------------------댓글---------------------------------------------------------------


    /**
     * 커뮤니티 id별로 모든 댓글 정보를 가져오는 API
     *
     * @param id 커뮤니티의 id
     * @return ResponseEntity<List < CommentResponse>> 200 OK, 댓글 정보 목록
     */
    @GetMapping("/board/{id}/comments")
    public ResponseEntity<List<CommunityCommentResponse>> readAllCommentByBoard(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(communityCommentService.readAllComment(id));
    }

    /**
     * 댓글 생성 API
     *
     * @param id      커뮤니티의 id
     * @param request 생성 정보 (parentSeq포함)
     * @return ResponseEntity<CommentResponse> 201 Created, 생성된 댓글 정보
     */
    @PostMapping("/board/{id}/comments")
    public ResponseEntity<CommunityCommentResponse> createComment(@PathVariable("id") long id, @RequestBody CommunityCommentCreate request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(communityCommentService.createComment(id, request));
    }

    /**
     * 댓글 수정 API
     *
     * @param id        커뮤니티의 id
     * @param commentId 댓글의 id
     * @param request   수정 정보
     */
    @PutMapping("/board/{id}/comments/{comment_id}")
    public ResponseEntity<Void> updateComment(@PathVariable("id") long id, @PathVariable("commentId") long commentId, @RequestBody CommunityCommentEdit request) {
        communityCommentService.UpdateComment(id, commentId, request);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * 댓글 삭제 API
     *
     * @param id        커뮤니티의 id
     * @param commentId 댓글의 id
     * @return ResponseEntity<Object> 204 No Content
     */
    @DeleteMapping("/board/{id}/comments/{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") long id, @PathVariable("commentId") long commentId) {
        communityCommentService.deleteComment(id, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
