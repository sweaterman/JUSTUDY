package com.justudy.backend.study.controller;

import com.justudy.backend.study.dto.request.*;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
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
    public ResponseEntity<List<StudyResponse>> readAllCommunityBySearch(@RequestParam("page") int page, @RequestParam("type") String type, @RequestParam("saerch") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(studyService.search(page, type, search));
    }
}
