package com.justudy.backend.controller;

import com.justudy.backend.dto.request.CommunityCreate;
import com.justudy.backend.dto.request.CommunityEdit;
import com.justudy.backend.dto.response.CommunityResponse;
import com.justudy.backend.service.CommunityService;
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
     * 모든 커뮤니티 정보를 가져오는 API
     *
     * @return ResponseEntity<List < CommunityResponse>> 200 OK, 커뮤니티 정보 목록
     */
    @GetMapping("/")
    public ResponseEntity<List<CommunityResponse>> readAllCommunity() {
        return ResponseEntity.status(HttpStatus.OK).body(service.readAllCommunity());
    }

    /**
     * 커뮤니티 상세 정보를 가져오는 API
     *
     * @param id 커뮤니티 sequence (PK)
     * @return ResponseEntity<CommunityResponse> 200 OK, 커뮤니티 상세 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommunityResponse> readCommunityById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readCommunity(id));
    }

    /**
     * 커뮤니티 생성 API
     *
     * @param request 생성 정보
     * @return ResponseEntity<CommunityResponse> 201 Created, 생성된 커뮤니티 정보
     */
    @PostMapping("")
    public ResponseEntity<CommunityResponse> createCommunity(@RequestBody CommunityCreate request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.readCommunity(service.createCommunity(request)));
    }

    /**
     * 커뮤니티 수정 API
     *
     * @param id            커뮤니티 sequence (PK)
     * @param request 수정 정보
     * @return ResponseEntity<UserResponse> 200 OK, 수정된 커뮤니티글 정보
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommunityResponse> updateCommunity(@PathVariable("id") long id, @RequestBody CommunityEdit request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.readCommunity(service.UpdateCommunity(id, request)));
    }

    /**
     * 커뮤니티 삭제 API
     *
     * @param id 커뮤니티 sequence (PK)
     * @return ResponseEntity<Object> 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCommunity(@PathVariable("id") long id) {
        service.deleteCommunity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
