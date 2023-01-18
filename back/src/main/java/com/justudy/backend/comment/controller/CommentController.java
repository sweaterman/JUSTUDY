package com.justudy.backend.comment.controller;

import com.justudy.backend.comment.dto.request.CommentCreate;
import com.justudy.backend.comment.dto.request.CommentEdit;
import com.justudy.backend.comment.dto.response.CommentResponse;
import com.justudy.backend.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    /**
     * 커뮤니티 id별로 모든 댓글 정보를 가져오는 API
     *
     * @param id 커뮤니티의 id
     * @return ResponseEntity<List < CommentResponse>> 200 OK, 댓글 정보 목록
     */
    @GetMapping("/board/{id}/comments")
    public ResponseEntity<List<CommentResponse>> readAllCommentByBoard(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readAllComment(id));
    }

    /**
     * 댓글 생성 API
     *
     * @param id      커뮤니티의 id
     * @param request 생성 정보 (parentSeq포함)
     * @return ResponseEntity<CommentResponse> 201 Created, 생성된 댓글 정보
     */
    @PostMapping("/board/{id}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable("id") long id, @RequestBody CommentCreate request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createComment(id, request));
    }

    /**
     * 댓글 수정 API
     *
     * @param id        커뮤니티의 id
     * @param commentId 댓글의 id
     * @param request   수정 정보
     */
    @PutMapping("/board/{id}/comments/{comment_id}")
    public ResponseEntity<Void> updateCommunity(@PathVariable("id") long id, @PathVariable("commentId") long commentId, @RequestBody CommentEdit request) {
        service.UpdateComment(id, commentId, request);
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
    public ResponseEntity<Void> deleteCommunity(@PathVariable("id") long id, @PathVariable("commentId") long commentId) {
        service.deleteComment(id, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
