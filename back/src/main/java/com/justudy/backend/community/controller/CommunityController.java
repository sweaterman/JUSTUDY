package com.justudy.backend.community.controller;

import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.response.CommunityResponse;
import com.justudy.backend.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService service;

    /**
     * 모든 검색 정보를 가져오는 API
     *
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
     */
    @GetMapping("/board")
    public ResponseEntity<List<CommunityResponse>> readAllCommunityBySearch(@RequestParam("page") int page, @RequestParam("type") String type, @RequestParam("saerch") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(service.search(page, type, search));
    }

    /**
     * 모든 커뮤니티 정보를 가져오는 API
     *
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
     */
    @GetMapping("/board")
    public ResponseEntity<List<CommunityResponse>> readAllCommunity(@RequestParam("page") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readAllCommunity(page));
    }

    /**
     * 커뮤니티 공지 정보를 가져오는 API
     *
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 공지 정보 목록
     */
    @GetMapping("/board/notice")
    public ResponseEntity<List<CommunityResponse>> readAllNoticeCommunity(@RequestParam("page") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readAllNoticeCommunity(page));
    }

    /**
     * 커뮤니티 상세 정보를 가져오는 API
     *
     * @param id 커뮤니티 sequence (PK)
     * @return ResponseEntity<CommunityResponse> 200 OK, 커뮤니티 상세 정보
     */
    @GetMapping("/board/{id}")
    public ResponseEntity<CommunityResponse> readCommunityById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readCommunity(id));
    }

    /**
     * 커뮤니티 생성 API
     *
     * @param request 생성 정보
     * @return ResponseEntity<CommunityResponse> 201 Created, 생성된 커뮤니티 정보
     */
    @PostMapping("/board")
    public ResponseEntity<CommunityResponse> createCommunity(@RequestBody CommunityCreate request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.readCommunity(service.createCommunity(request)));
    }

    /**
     * 커뮤니티 수정 API
     *
     * @param id      커뮤니티 sequence (PK)
     * @param request 수정 정보
     * @return ResponseEntity<UserResponse> 200 OK, 수정된 커뮤니티글 정보
     */
    @PutMapping("/board/{id}")
    public ResponseEntity<CommunityResponse> updateCommunity(@PathVariable("id") long id, @RequestBody CommunityEdit request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readCommunity(service.UpdateCommunity(id, request)));
    }

    /**
     * 커뮤니티 삭제 API
     *
     * @param id 커뮤니티 sequence (PK)
     * @return ResponseEntity<Object> 204 No Content
     */
    @DeleteMapping("/board/{id}")
    public ResponseEntity<Object> deleteCommunity(@PathVariable("id") long id) {
        service.deleteCommunity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 북마크 생성 API
     *
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
     */
    @PostMapping("/board/bookmark")
    public ResponseEntity<CommunityResponse> createBookmark(CommunityCreate request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createBookmark(request));
    }
//
//    /**
//     * 북마크 삭제 API
//     *
//     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
//     */
//    @DeleteMapping("/board/bookmark")
//    public ResponseEntity<List<CommunityResponse>> createBookmark() {
//        return ResponseEntity.status(HttpStatus.OK).body(service.deleteBookmark());
//    }
//    /**
//     * 북마크 불러오기 API
//     *
//     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
//     */
//    @GetMapping("/board/bookmark/{id}")
//    public ResponseEntity<List<CommunityResponse>> createBookmark() {
//        return ResponseEntity.status(HttpStatus.OK).body(service.readAllBookmark());
//    }
}
