package com.justudy.backend.community.controller;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.community.dto.request.*;
import com.justudy.backend.community.dto.response.CommunityCommentResponse;
import com.justudy.backend.community.dto.response.CommunityDetailResponse;
import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.community.dto.response.ListResult;
import com.justudy.backend.community.service.CommunityBookmarkService;
import com.justudy.backend.community.service.CommunityCommentService;
import com.justudy.backend.community.service.CommunityLoveService;
import com.justudy.backend.community.service.CommunityService;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {

    private final MemberService memberService;
    private final CommunityService communityService;
    private final CommunityLoveService communityLoveService;
    private final CommunityBookmarkService communityBookmarkService;
    private final CommunityCommentService communityCommentService;
    private final CategoryService categoryService;

    /**
     * page - default 0
     * size - default 20
     * category
     * type - nickname, title, content
     * search - 검색 내용
     * order - view, like
     */
    @GetMapping("/board")
    public ListResult<List<CommunityListResponse>> getList(@ModelAttribute CommunitySearch condition) {
        return communityService.getCommunities(condition.validateNull());
    }

    @GetMapping("/board/notices")
    public ListResult<List<CommunityListResponse>> getNotices(@PageableDefault(size = 20) Pageable pageable) {
        return communityService.getNotices(pageable);
    }

    @GetMapping("/board/popular")
    public List<CommunityListResponse> getMostPopular(@PageableDefault(size = 10) Pageable pageable) {
        return communityService.getMostLoveCommunitiesOfWeek(pageable);
    }


    /**
     * 커뮤니티 상세 정보를 가져오는 API
     *
     * @param communitySequence PK
     * @return ResponseEntity<CommunityResponse> 200 OK, 커뮤니티 상세 정보
     */
    @GetMapping("/board/{boardId}")
    public ResponseEntity<CommunityDetailResponse> readCommunityById(@PathVariable("boardId") Long communitySequence) {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.readCommunityDetail(communitySequence));
    }

    /**
     * 커뮤니티 생성 API
     *
     * @param request 생성 정보
     * @return ResponseEntity<CommunityResponse> 201 Created, 생성된 커뮤니티 정보
     */
    @PostMapping("/board")
    public ResponseEntity<CommunityDetailResponse> createCommunity(@RequestBody CommunityCreate request, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        MemberEntity findMember = memberService.getMember(loginSequence);
        CategoryEntity category = categoryService.getCategoryEntityByKey(request.getCategory());

        CommunityDetailResponse response = communityService.createCommunity(request, findMember, category);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 커뮤니티 수정 API
     *
     * @param communitySequence      커뮤니티 sequence (PK)
     * @param request 수정 정보
     * @return ResponseEntity<UserResponse> 200 OK, 수정된 커뮤니티글 정보
     */
    @PutMapping("/board/{id}")
    public ResponseEntity<CommunityDetailResponse> updateCommunity(@PathVariable("id") Long communitySequence,
                                                                   @RequestBody CommunityEdit request,
                                                                   HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        CommunityDetailResponse response = communityService.updateCommunity(loginSequence, communitySequence, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 커뮤니티 삭제 API
     *
     * @param communitySequence 커뮤니티 sequence (PK)
     * @return ResponseEntity<Void> 204 No Content
     */
    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable("boardId") long communitySequence, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        communityService.deleteCommunity(loginSequence, communitySequence);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // ---------------------------------------------------------------북마크---------------------------------------------------------------

    /**
     * 북마크 생성 API - DONE
     * @param communitySequence
     * @param session
     */
    @PostMapping("/board/{id}/bookmark")
    public ResponseEntity<Void> createBookmark(@PathVariable("id") Long communitySequence,
                                               HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        communityBookmarkService.createBookmark(loginSequence, communitySequence);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    /**
     * 북마크 삭제 API
     *
     * @param communitySequence 커뮤니티 sequence (PK)
     * @return ResponseEntity<List < CommunityResponse>> 204 NO_CONTENT'
     * 북마크는 유저기능아닌가? 매핑 바꿔야할듯
     * 복합키로 delete 어캐구현하지
     */
    @DeleteMapping("/board/{id}/bookmark")
    public ResponseEntity<Void> deleteBookmark(@PathVariable("id") Long communitySequence,
                                               HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        communityBookmarkService.deleteBookmark(loginSequence, communitySequence);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // ---------------------------------------------------------------좋아요---------------------------------------------------------------

    /**
     * 좋아요 생성 API
     */
    @PostMapping("/board/{id}/love")
    public ResponseEntity<Void> createLove(@PathVariable("id") Long communitySequence,
                                           HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        communityLoveService.createLove(loginSequence, communitySequence);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    /**
     * 좋아요 삭제 API
     */
    @DeleteMapping("/board/{id}/love")
    public ResponseEntity<Void> deleteLove(@PathVariable("id") Long communitySequence,
                                           HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        communityLoveService.deleteLove(loginSequence, communitySequence);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
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




    /**
     * 모든 검색 정보를 가져오는 API
     *
     * @param page   페이지 넘버
     * @param type   검색 분류(아이디, 제목, 내용)
     * @param search 검색할 값
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
     */
//    @GetMapping("/board/search")
    public ResponseEntity<List<CommunityDetailResponse>> readAllCommunityBySearch(@RequestParam("page") int page, @RequestParam("type") String type, @RequestParam("saerch") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.search(page, type, search));
    }
    /**
     * 일주일간 좋아요 많은 커뮤니티 정보를 가져오는 API
     *
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
     */
//    @GetMapping("/board/love")
    public ResponseEntity<List<CommunityDetailResponse>> readPopularCommunity() {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.readPopularCommunity());
    }

    /**
     * 커뮤니티 공지 정보를 가져오는 API
     *
     * @param page 페이지 넘버
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 공지 정보 목록
     */
//    @GetMapping("/board/notice")
    public ResponseEntity<List<CommunityDetailResponse>> readAllNoticeCommunity(@RequestParam("page") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(communityService.readAllNoticeCommunity(page));
    }
}
