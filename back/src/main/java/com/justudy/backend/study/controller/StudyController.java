package com.justudy.backend.study.controller;

import com.justudy.backend.category.dto.request.CategoryResponse;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.infra.ImageConst;
import com.justudy.backend.file.service.UploadFileService;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyEdit;
import com.justudy.backend.study.dto.request.StudyMemberCreate;
import com.justudy.backend.study.dto.request.StudyResumeApply;
import com.justudy.backend.study.dto.response.*;
import com.justudy.backend.member.service.MemberService;
import com.justudy.backend.study.service.StudyFrequencyService;
import com.justudy.backend.study.service.StudyMemberService;
import com.justudy.backend.study.service.StudyResumeService;
import com.justudy.backend.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final CategoryService categoryService;
    private final MemberService memberService;
    private final StudyFrequencyService studyFrequencyService;
    private final StudyResumeService studyResumeService;
    private final StudyMemberService studyMemberService;
    private final UploadFileService uploadFileService;

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
     * @param type   검색 분류(스터디명, 스터디장)
     * @param search 검색 내용
     * @return ResponseEntity<List < StudyResponse>> 200 OK, 스터디 정보 목록
     * 페이징, 검색,
     */
    @GetMapping("/")
    public ResponseEntity<StudySearchResponse> readAllStudy(@RequestParam("page") int page, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "search", required = false) String search) {
        //todo session 과 id 체크
//        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        List<String> subCategories = null;
        //타입이 카테고리 검색이면
        if (type != null && type.compareTo("category") == 0) {
            subCategories = Arrays.asList(search.split(","));
            List<CategoryResponse> response = categoryService.getSubCategories();
            for (CategoryResponse categoryResponse : response) {
                int flg = 0;
                for (String subCategory : subCategories) {
                    if (subCategory.compareTo(categoryResponse.getValue()) == 0) {
                        flg = 1;
                        break;
                    }
                }
                if (flg == 0) throw new InvalidRequest();
            }
            search = null;
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
    public ResponseEntity<StudyDetailResponse> readStudy(@PathVariable("id") Long id) {
        //todo session 과 id 체크

        // 뱃지순으로 정렬
        return ResponseEntity.status(HttpStatus.OK).body(studyService.readDetailStudy(id));
    }

    /**
     * 스터디를 생성하는 API
     *
     * @param request 생성정보
     * @return ResponseEntity<StudyDetailResponse> 201 CREATE, 생성된 스터디 정보
     */
    @PostMapping("/")
    public ResponseEntity<StudyDetailResponse> createStudy(@RequestBody StudyCreate request) {
        //todo session 과 id 체크

        //todo 이미지 생성
        UploadFileEntity basicImage = uploadFileService.getUploadFile(ImageConst.BASIC_MEMBER_IMAGE);//기본 이미지 파일, 1L

        //스터디 생성

        Long studySeq = studyService.createStudy(request, basicImage);

        //받은 활동주기 생성
        request.getFrequency().forEach(studyFrequencyCreate -> studyFrequencyService.createStudyFrequency(studySeq, studyFrequencyCreate));

        //스터디맴버 리더 추가
        //todo nickname으로 검색
        Long leaderSeq = 1L;
//        Long leaderSeq = memberService;
        studyMemberService.createStudyMember(StudyMemberCreate
                .builder()
                .studySeq(studySeq)
                .memberSeq(leaderSeq)
                .build());
        //todo 스터디 룸 생성

        return ResponseEntity.status(HttpStatus.CREATED).body(studyService.readStudy(studySeq));
    }

    /**
     * 스터디를 수정하는 API
     *
     * @param id      스터디 sequence (PK)
     * @param request 수정정보
     * @return ResponseEntity<StudyDetailResponse> 200 OK, 수정된 스터디 정보
     * 스터디 맴버나 지원서는 수정하지 않음
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudyDetailResponse> updateStudy(@PathVariable("id") Long id, @RequestBody StudyEdit request) {
        //todo session 과 id 체크

        //todo 이미지 수정

        //활동주기 수정
        studyFrequencyService.deleteStudyFrequencyByStudy(id);
        studyFrequencyService.createStudyFrequencies(id, request.getFrequency());

        //스터디 수정
        Long studySeq = studyService.updateStudy(id, request);

        //스터디 맴버 수정 이건 acceptStudyResume API에서 추가
        return ResponseEntity.status(HttpStatus.OK).body(studyService.readStudy(studySeq));
    }

    /**
     * 스터디를 삭제하는 API
     *
     * @param id 스터디 sequence (PK)
     * @return ResponseEntity<Void> 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudy(@PathVariable("id") Long id) {
        //todo session 과 id 체크

        //todo 이미지 삭제

        // 활동주기 삭제
        studyFrequencyService.deleteStudyFrequencyByStudy(id);

        // 스터디 맴버 삭제
        studyMemberService.deleteStudyMemberByStudy(id);

        //todo 스터디룸 삭제

        //스터디 삭제
        studyService.deleteStudy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 스터디 이름 중복체크 API
     *
     * @param name 스터디 이름
     * @return ResponseEntity<Void> 없을시 200 OK 중복일시 409 CONFLICT
     */
    @GetMapping("/check/{name}")
    public ResponseEntity<Void> checkStudyName(@PathVariable("name") String name) {
        //todo 이름으로 스터디 검색
        if (studyService.checkNickName(name))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }


    // ---------------------------------------------------------------스터디 지원---------------------------------------------------------------

    /**
     * 내가 지원한 스터디 목록을 가져오는 API
     *
     * @param id 멤버 id
     * @return ResponseEntity<List < StudyResponse>> 200 OK, 스터디 지원목록 정보
     */
    @GetMapping("/mystudy/apply/{id}")
    public ResponseEntity<List<StudyResponse>> readAllApplyStudy(@PathVariable("id") Long id) {
        //todo session 과 id 체크
        return ResponseEntity.status(HttpStatus.OK).body(studyResumeService.readAllApplyStudy(id));
    }

//    /**
//     * 스터디 지원목록을 생성하는 API
//     *
//     * @param id      지원서 id
//     * @param request 생성정보
//     * @return ResponseEntity<StudyResumeResponse> 201 CREATE, 생성된 스터디 지원목록
//     */
//    @PostMapping("/apply/{id}")
//    public ResponseEntity<StudyResumeResponse> createStudyResume(@PathVariable("id") Long id, @RequestBody StudyResumeCreate request) {
//        Long resumeSeq = studyResumeService.createStudyResume(id, request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(studyResumeService.readStudyResume(resumeSeq));
//    }

    /**
     * 스터디 지원목록을 삭제하는 API
     *
     * @param id 지원서 id
     * @param id 스터디 sequence (PK)
     * @return ResponseEntity<Void> 204 NO CONTENT
     */
    @DeleteMapping("/apply/{id}")
    public ResponseEntity<Void> deleteStudyResume(@PathVariable("id") Long id) {
        studyResumeService.deleteStudyResume(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 스터디 내의 지원목록 가져오는 API
     *
     * @param id 스터디 id
     * @return ResponseEntity<List < StudyResumeResponse>> 200 OK, 스터디 지원목록 정보
     */
    @GetMapping("/{id}/members/apply")
    public ResponseEntity<List<StudyResumeResponse>> readAllResumeByStudy(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studyResumeService.readAllStudyResumeByStudy(id));
    }

    // ---------------------------------------------------------------스터디 활동주기---------------------------------------------------------------

    /**
     * 선택한 스터디 활동주기를 가져오는 API
     *
     * @param id 스터디 id
     * @return ResponseEntity<List < StudyFrequencyResponse>> 200 OK, 스터디 활동주기 정보
     */
    @GetMapping("/{id}/frequency")
    public ResponseEntity<List<StudyFrequencyResponse>> readAllFrequency(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studyFrequencyService.readAllStudyFrequency(id));
    }

//    /**
//     * 스터디 활동주기를 생성하는 API
//     *
//     * @param request 생성정보
//     * @return ResponseEntity<StudyFrequencyResponse> 201 CREATE, 생성된 스터디 활동주기
//     */
//    @PostMapping("/{id}/frequency")
//    public ResponseEntity<StudyFrequencyResponse> createStudyFrequency(@PathVariable("id") Long id, @RequestBody StudyFrequencyCreate request) {
//        // 활동주기 생성
//        Long frequencySeq = studyFrequencyService.createStudyFrequency(id, request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(studyFrequencyService.readStudyFrequency(frequencySeq));
//    }
//
//    /**
//     * 스터디 활동주기를 수정하는 API
//     *
//     * @param id          스터디 sequence (PK)
//     * @param frequencyId 스터디 활동주기 sequence (PK)
//     * @param request     수정정보
//     * @return ResponseEntity<StudyFrequencyResponse> 200 OK, 수정된 스터디 활동주기 정보
//     */
//    @PutMapping("/{id}/frequency/{frequencyid}")
//    public ResponseEntity<StudyFrequencyResponse> updateStudyFrequency(@PathVariable("id") Long id, @PathVariable("frequencyid") Long frequencyId, @RequestBody StudyFrequencyEdit request) {
//        // 활동주기 수정
//        Long frequencySeq = studyFrequencyService.updateStudyFrequency(id, frequencyId, request);
//        return ResponseEntity.status(HttpStatus.OK).body(studyFrequencyService.readStudyFrequency(frequencySeq));
//    }
//
//    /**
//     * 스터디 활동주기를 삭제하는 API
//     *
//     * @param id          스터디 sequence (PK)
//     * @param frequencyId 스터디 활동주기 sequence (PK)
//     * @return ResponseEntity<Void> 204 NO CONTENT
//     */
//    @DeleteMapping("/{id}/frequency/{frequencyid}")
//    public ResponseEntity<Void> deleteStudyFrequency(@PathVariable("id") Long id, @PathVariable("frequencyid") Long frequencyId) {
//        // 활동주기 삭제
//        studyFrequencyService.deleteStudyFrequency(id, frequencyId);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//    }


    // ---------------------------------------------------------------스터디 맴버---------------------------------------------------------------

    /**
     * 스터디장 위임하는 API
     *
     * @param id       스터디 sequence (PK)
     * @param memberId 스터디 sequence (PK)
     * @return ResponseEntity<Void> 200 OK, 수정된 스터디장 정보
     */
    @PutMapping("/{id}/members/{memberid}")
    public ResponseEntity<Void> updateStudyLeader(@PathVariable("id") Long id, @PathVariable("memberid") Long memberId) {
        //todo session 과 id 체크

        studyMemberService.updateStudyLeader(id, memberId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    /**
     * 스터디원 추방 API
     *
     * @param id       스터디 id
     * @param memberId 멤버 id
     * @return ResponseEntity<Void> 204 NO CONTENT
     */
    @DeleteMapping("/{id}/members/{memberid}")
    public ResponseEntity<Void> exileStudyMember(@PathVariable("id") Long id, @PathVariable("memberid") Long memberId) {
        //todo session 과 리더 id 체크

        studyMemberService.exileStudyMember(id, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 스터디 탈퇴 API
     *
     * @param id       스터디 id
     * @param memberId 멤버 id
     * @return ResponseEntity<Void> 204 NO CONTENT
     * 스터디 리더일시 탈퇴 불가능
     */
    @DeleteMapping("/{id}/withdraw/{memberid}")
    public ResponseEntity<Void> withdrawStudyMember(@PathVariable("id") Long id, @PathVariable("memberid") Long memberId) {
        //todo session 과  리더 id 체크 리더일시 탈퇴 불가능
        //todo session 과 memberid 체크 아닐시 에러
        studyMemberService.withdrawStudyMember(id, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 내가 가입한 스터디 목록을 가져오는 API
     *
     * @param id 멤버 id
     * @return ResponseEntity<List < StudyResponse>> 200 OK, 가입한 스터디 목록 정보
     */
    @GetMapping("/mystudy/register/{id}")
    public ResponseEntity<List<StudyResponse>> readAllRegisterStudy(@PathVariable("id") Long id) {
        //todo session 과 id 체크
        return ResponseEntity.status(HttpStatus.OK).body(studyMemberService.readAllRegisterStudy(id));
    }

    /**
     * 스터디 신청 업데이트 API
     *
     * @param id      멤버 id
     * @param applyId 지원서 id
     * @param request isAccept
     * @return ResponseEntity<List < StudyResponse>> 200 OK
     */
    @PutMapping("/study/{id}/members/apply/{applyid}")
    public ResponseEntity<Void> acceptStudyResume(@PathVariable("id") Long id, @PathVariable("applyid") Long applyId, @RequestBody StudyResumeApply request) {
        //todo session 과 leader id 체크

        StudyDetailResponse studyEntity = studyService.readStudy(id);
        //지원서 체크
        Long resumeSeq = studyEntity.getResumeSeq()
                .stream()
                .filter(resume -> resume.longValue() == applyId.longValue())
                .findFirst()
                .orElseThrow(InvalidRequest::new);

        //스터티 승인
        if (request.getIsAccept()) {
            //스터디 인원 꽉찼는지 확인
            if (studyEntity.getMember().size() >= studyService.readStudy(id).getPopulation())
                throw new InvalidRequest("", "인원이 꽉찼습니다");

            ///스터디 맴버 업데이트
            StudyResumeResponse studyResumeResponse = studyResumeService.readStudyResume(resumeSeq);
            studyMemberService.createStudyMember(StudyMemberCreate
                    .builder()
                    .studySeq(studyResumeResponse.getStudySeq())
                    .memberSeq(studyResumeResponse.getMemberSeq())
                    .build());
        }

        //지원서 삭제
        studyResumeService.deleteStudyResume(resumeSeq);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //스터디 게시글 목록
    //스터디 게시글 상세보기
    //스터디 게시글 수정
    //스터디 게시글 삭제

    //스터디 댓글 목록
    //스터디 댓글 상세보기
    //스터디 댓글 수정
    //스터디 댓글 삭제


}
