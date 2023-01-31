package com.justudy.backend.studyMember.controller;

import com.justudy.backend.studyMember.dto.response.StudyMemberResponse;
import com.justudy.backend.studyMember.service.StudyMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/studymember")
@RequiredArgsConstructor
public class StudyMemberController {

    private final StudyMemberService studyMemberService;
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
    public ResponseEntity<List<StudyMemberResponse>> readAllCommunityBySearch(@RequestParam("page") int page, @RequestParam("type") String type, @RequestParam("saerch") String search) {
        return ResponseEntity.status(HttpStatus.OK).body(studyMemberService.search(page, type, search));
    }
}
