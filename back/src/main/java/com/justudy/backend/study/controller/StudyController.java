package com.justudy.backend.study.controller;

import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.study.dto.request.*;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.exception.InvalidRequest;
import com.justudy.backend.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final CategoryService categoryService;
    // ---------------------------------------------------------------스터디---------------------------------------------------------------

//    /**
//     * 공개된 모든 스터디 정보를 가져오는 API
//     *
//     * @param page   페이지 넘버
//     * @param type   검색 분류(스터디명, 스터디장)
//     * @param search 검색 내용
//     * @return ResponseEntity<List < StudyResponse>> 200 OK, 스터디 정보 목록
//     * 정렬 기준 생성시간 남은 인원수?
//     */
//    @GetMapping("/")
//    public ResponseEntity<List<StudyResponse>> readAllStudy(@RequestParam("page") int page, @RequestParam("type") String type, @RequestParam("search") String search) {
//        return ResponseEntity.status(HttpStatus.OK).body(studyService.search(page, type, search));
//    }

    /**
     * 공개된 스터디 정보를 가져오는 API
     *
     * @param page   페이지 넘버
     * @param sub    서브카테고리{ 구분자 ,로 구분}
     * @param type   검색 분류(스터디명, 스터디장)
     * @param search 검색 내용
     * @return ResponseEntity<List < StudyResponse>> 200 OK, 스터디 정보 목록
     * 정렬 기준 생성시간 남은 인원수?
     */
    @GetMapping("/")
    public ResponseEntity<Slice<StudyResponse>> readAllStudy(@RequestParam("page") int page, @RequestParam("sub") String sub, @RequestParam("type") String type, @RequestParam("search") String search) {
        List<String> subCategories = null;
        if (sub != null) {
            subCategories = Arrays.asList(sub.split(","));
            List<String> response = categoryService.getSubCategories();
            for (String subCategory : subCategories) {
                if (response.contains(subCategory) == false)
                    throw new InvalidRequest();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(studyService.search(page, subCategories, type, search));
    }

    /**
     * 스터디 상세 정보를 가져오는 API
     *
     * @param id 스터디 sequence (PK)
     * @return ResponseEntity<StudyResponse> 200 OK, 스터디 정보 목록
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudyResponse> readStudy(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studyService.readStudy(id));
    }

    /**
     * 스터디를 생성하는 API
     *
     * @param request 생성정보
     * @return ResponseEntity<StudyResponse> 201 CREATE, 생성된 스터디 정보
     */
    @PostMapping("/")
    public ResponseEntity<StudyResponse> createStudy(@RequestBody StudyCreate request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studyService.readStudy(studyService.createStudy(request)));
    }

    /**
     * 스터디를 수정하는 API
     *
     * @param id      스터디 sequence (PK)
     * @param request 수정정보
     * @return ResponseEntity<StudyResponse> 200 OK, 수정된 스터디 정보
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudyResponse> updateStudy(@PathVariable("id") Long id, @RequestBody StudyEdit request) {
        return ResponseEntity.status(HttpStatus.OK).body(studyService.readStudy(studyService.updateStudy(id, request)));
    }

    /**
     * 스터디를 삭제하는 API
     *
     * @param id 스터디 sequence (PK)
     * @return ResponseEntity<Void> 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudy(@PathVariable("id") Long id) {
        studyService.deleteStudy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // ---------------------------------------------------------------스터디 지원---------------------------------------------------------------
}
