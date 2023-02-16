package com.justudy.backend.study.controller;

import com.justudy.backend.GroupCall.service.StudyRoomService;
import com.justudy.backend.admin.service.AdminService;
import com.justudy.backend.category.dto.request.CategoryResponse;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.response.CommunityListResult;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.infra.ImageConst;
import com.justudy.backend.file.service.FileStore;
import com.justudy.backend.file.service.UploadFileService;
import com.justudy.backend.login.infra.SessionConst;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.dto.request.*;
import com.justudy.backend.study.dto.request.community.StudyCommunityCommentCreate;
import com.justudy.backend.study.dto.request.community.StudyCommunityCommentEdit;
import com.justudy.backend.study.dto.request.community.StudyCommunityCreate;
import com.justudy.backend.study.dto.request.community.StudyCommunityEdit;
import com.justudy.backend.study.dto.response.*;
import com.justudy.backend.member.service.MemberService;
import com.justudy.backend.study.dto.response.community.StudyCommunityCommentResponse;
import com.justudy.backend.study.dto.response.community.StudyCommunityDetailResponse;
import com.justudy.backend.study.dto.response.community.StudyCommunityListResponse;
import com.justudy.backend.study.service.StudyFrequencyService;
import com.justudy.backend.study.service.StudyMemberService;
import com.justudy.backend.study.service.StudyResumeService;
import com.justudy.backend.study.service.StudyService;
import com.justudy.backend.study.service.community.StudyCommunityBookmarkService;
import com.justudy.backend.study.service.community.StudyCommunityCommentService;
import com.justudy.backend.study.service.community.StudyCommunityLoveService;
import com.justudy.backend.study.service.community.StudyCommunityService;
import com.justudy.backend.timer.service.RoomActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final int MAX_STUDY_PAGE_SIZE = 9;
    private final StudyService studyService;
    private final CategoryService categoryService;
    private final MemberService memberService;
    private final StudyFrequencyService studyFrequencyService;
    private final StudyResumeService studyResumeService;
    private final StudyMemberService studyMemberService;
    private final UploadFileService uploadFileService;
    private final FileStore fileStore;
    private final StudyRoomService studyRoomService;

    private final StudyCommunityService studyCommunityService;
    private final StudyCommunityLoveService studyCommunityLoveService;
    private final StudyCommunityBookmarkService studyCommunityBookmarkService;
    private final StudyCommunityCommentService studyCommunityCommentService;
    private final RoomActivityService roomActivityService;
    ;
    private final AdminService adminService;
    // ---------------------------------------------------------------스터디---------------------------------------------------------------

    /**
     * 공개된 스터디 정보를 가져오는 API
     *
     * @param pageable 페이지 넘버
     * @param type     검색 분류(스터디명, 스터디장)
     * @param search   검색 내용
     * @return ResponseEntity<List < StudyResponse>> 200 OK, 스터디 정보 목록
     * 페이징, 검색,
     */
    @GetMapping("")
    public ResponseEntity<StudySearchResponse> readAllStudy(@PageableDefault(size = MAX_STUDY_PAGE_SIZE) Pageable pageable, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "search", required = false) String search) {
        List<String> Categories = null;
        //타입이 카테고리 검색이면
        if (type != null && type.compareTo("category") == 0) {
            Categories = Arrays.asList(search.split(","));
            int flg = 0;
            List<CategoryResponse> response = categoryService.getSubCategories();
            for (CategoryResponse categoryResponse : response) {
                for (String subCategory : Categories) {
                    if (subCategory.compareTo(categoryResponse.getKey()) == 0) {
                        flg = 1;
                        break;
                    }
                }
            }
            response = categoryService.getSubCategories();
            for (CategoryResponse categoryResponse : response) {
                for (String mainCategory : Categories) {
                    if (mainCategory.compareTo(categoryResponse.getKey()) == 0) {
                        flg = 1;
                        break;
                    }
                }
            }
            if (flg == 0) throw new InvalidRequest();
            search = null;
        }

        return ResponseEntity.status(HttpStatus.OK).body(studyService.search(pageable, Categories, type, search));
    }


    /**
     * 스터디 상세 정보를 가져오는 API
     *
     * @param id 스터디 sequence (PK)
     * @return ResponseEntity<StudyResponse> 200 OK, 스터디 정보 목록
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudyDetailResponse> readStudy(@PathVariable("id") Long id, HttpSession session) {
        //session 과 id 체크
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);


        // 뱃지순으로 정렬
        return ResponseEntity.status(HttpStatus.OK).body(studyService.readDetailStudy(id, loginSequence));
    }

    /**
     * 스터디를 생성하는 API
     *
     * @param request 생성정보
     * @return ResponseEntity<StudyDetailResponse> 201 CREATE, 생성된 스터디 정보
     */
    @PostMapping("")
    public ResponseEntity<StudyDetailResponse> createStudy(@RequestPart(name = "request") @Validated StudyCreate request, @RequestPart(name = "file", required = false) MultipartFile multipartFile, HttpSession session) throws IOException {
        //session 과 id 체크
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        request.changeLeaderSeq(loginSequence);

        //이미지 생성
        UploadFileEntity uploadImage = uploadFileService.getUploadFile(ImageConst.BASIC_MEMBER_IMAGE);//기본 이미지 파일, 1L
        if (multipartFile != null) {
            uploadImage = fileStore.storeFile(multipartFile);
            uploadFileService.saveUploadFile(uploadImage);
        }
        //스터디 생성
        Long studySeq = studyService.createStudy(request, uploadImage);

        //받은 활동주기 생성
        request.getFrequency().forEach(studyFrequencyCreate -> {
            try {
                studyFrequencyService.createStudyFrequency(studySeq, studyFrequencyCreate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        //스터디맴버 리더 추가
        studyMemberService.createStudyMember(StudyMemberCreate
                .builder()
                .studySeq(studySeq)
                .memberSeq(loginSequence)
                .build());
        //스터디 룸 생성
        studyRoomService.saveStudyRoom(studySeq);


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
    public ResponseEntity<StudyDetailResponse> updateStudy(@PathVariable("id") Long id, @RequestPart(name = "request") @Validated StudyEdit request,
                                                           @RequestPart(name = "file", required = false) MultipartFile multipartFile, HttpSession session) throws IOException, ParseException {
        //session 과 id 체크
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        Long leader = studyService.readStudy(id).getLeaderSeq();
        if (loginSequence.longValue() != leader.longValue()) {
            throw new InvalidRequest("", "리더가 아니면 스터디를 수정할 수 없습니다.");
        }

        //이미지 수정
        //파일 없을시 변경 x
        UploadFileEntity uploadImage = uploadFileService.getUploadFile(ImageConst.BASIC_MEMBER_IMAGE);//기본 이미지 파일, 1L
        if (multipartFile != null) {
            uploadImage = fileStore.storeFile(multipartFile);
            uploadFileService.saveUploadFile(uploadImage);
        }
        //활동주기 수정
        studyFrequencyService.deleteStudyFrequencyByStudy(id);
        studyFrequencyService.createStudyFrequencies(id, request.getFrequency());


        //스터디 수정
        Long studySeq = studyService.updateStudy(id, request, uploadImage);

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
    public ResponseEntity<Void> deleteStudy(@PathVariable("id") Long id, HttpSession session) {
        //session 과 id 체크
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        if (loginSequence != studyService.getStudyLeader(id).longValue() && !adminService.validateAdmin(loginSequence)) {
            throw new InvalidRequest("", "리더가 아니면 스터디를 삭제할 수 없습니다.");
        }
        //todo 이미지 삭제

        // 활동주기 삭제
        studyFrequencyService.deleteStudyFrequencyByStudy(id);

        // 스터디 맴버 삭제
        studyMemberService.deleteStudyMemberByStudy(id);

        //활동기록 삭제
        roomActivityService.deleteRoomActivity(id);

        //스터디룸 삭제
        studyRoomService.deleteStudyRoom(id);

        //스터디 지원서 삭제
        studyResumeService.deleteStudyResumeByStudy(id);


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
        if (studyService.checkNickName(name))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }


    // ---------------------------------------------------------------스터디 지원---------------------------------------------------------------

    /**
     * 내가 지원한 스터디 목록을 가져오는 API
     *
     * @return ResponseEntity<List < StudyResponse>> 200 OK, 스터디 지원목록 정보
     */
    @GetMapping("/mystudy/apply")
    public ResponseEntity<List<StudyResponse>> readAllApplyStudy(HttpSession session) {
        //session 과 id 체크
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return ResponseEntity.status(HttpStatus.OK).body(studyResumeService.readAllApplyStudy(loginSequence));
    }

    /**
     * 스터디 지원목록을 생성하는 API
     *
     * @param request 생성정보
     * @return ResponseEntity<StudyResumeResponse> 201 CREATE, 생성된 스터디 지원목록
     */
    @PostMapping("/apply")
    public ResponseEntity<StudyResumeResponse> createStudyResume(@RequestBody StudyResumeCreate request, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        request.changeMemberSeq(loginSequence);

        Long resumeSeq = studyResumeService.createStudyResume(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(studyResumeService.readStudyResume(resumeSeq));
    }

    /**
     * 스터디 지원목록을 삭제하는 API
     *
     * @param id 스터디 id
     * @return ResponseEntity<Void> 204 NO CONTENT
     */
    @DeleteMapping("/apply/{id}")
    public ResponseEntity<Void> deleteStudyResume(@PathVariable("id") Long id, HttpSession session) {
        //session
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        studyResumeService.deleteStudyResume(id, loginSequence);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 스터디 내의 지원목록 가져오는 API
     *
     * @param id 스터디 id
     * @return ResponseEntity<List < StudyResumeResponse>> 200 OK, 스터디 지원목록 정보
     */
    @GetMapping("/{id}/members/apply")
    public ResponseEntity<List<StudyResumeResponse>> readAllResumeByStudy(@PathVariable("id") Long id, HttpSession session) {
        //session
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return ResponseEntity.status(HttpStatus.OK).body(studyResumeService.readAllStudyResumeByStudy(id, loginSequence));
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
     * @param memberId 멤버 sequence (PK)
     * @return ResponseEntity<Void> 200 OK, 수정된 스터디장 정보
     */
    @PutMapping("/{id}/members/{memberid}")
    public ResponseEntity<Void> updateStudyLeader(@PathVariable("id") Long id, @PathVariable("memberid") Long memberId, HttpSession session) {
        //session 과 id 체크
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        if (loginSequence.longValue() != studyService.getStudyLeader(id).longValue()) throw new InvalidRequest();

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
    public ResponseEntity<Void> exileStudyMember(@PathVariable("id") Long id, @PathVariable("memberid") Long memberId, HttpSession session) {
        //session 과 리더 id 체크
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        if (loginSequence.longValue() != studyService.getStudyLeader(id).longValue()) throw new InvalidRequest();

        studyMemberService.exileStudyMember(id, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 스터디 탈퇴 API
     *
     * @param id 스터디 id
     * @return ResponseEntity<Void> 204 NO CONTENT
     * 스터디 리더일시 탈퇴 불가능
     */
    @DeleteMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdrawStudyMember(@PathVariable("id") Long id, HttpSession session) {
        //session 과  리더 id 체크 리더일시 탈퇴 불가능
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        if (loginSequence.longValue() == studyService.getStudyLeader(id).longValue()) throw new InvalidRequest();

        studyMemberService.withdrawStudyMember(id, loginSequence);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    /**
     * 내가 가입한 스터디 목록을 가져오는 API
     *
     * @return ResponseEntity<List < StudyResponse>> 200 OK, 가입한 스터디 목록 정보
     */
    @GetMapping("/mystudy/register")
    public ResponseEntity<List<StudyResponse>> readAllRegisterStudy(HttpSession session) {
        //session
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        return ResponseEntity.status(HttpStatus.OK).body(studyMemberService.readAllRegisterStudy(loginSequence));
    }

    /**
     * 스터디 신청 업데이트 API
     *
     * @param id      스터디 id
     * @param applyId 지원서 id
     * @param request isAccept
     * @return ResponseEntity<Void> 200 OK
     */
    @PutMapping("/{id}/members/apply/{applyid}")
    public ResponseEntity<Void> acceptStudyResume(@PathVariable("id") Long id, @PathVariable("applyid") Long applyId, @RequestBody StudyResumeApply request, HttpSession session) {
        //session 과 leader id 체크
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        if (loginSequence.longValue() != studyService.getStudyLeader(id).longValue()) throw new InvalidRequest();

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
        studyResumeService.deleteById(id, resumeSeq);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // ---------------------------------------------------------------스터디 게시글---------------------------------------------------------------

    //스터디 게시글 목록
    //스터디 게시글 상세보기
    //스터디 게시글 수정
    //스터디 게시글 삭제

    //스터디 댓글 목록
    //스터디 댓글 상세보기
    //스터디 댓글 수정
    //스터디 댓글 삭제

    /**
     * page - default 0
     * size - default 20
     * type - nickname, title, content
     * search - 검색 내용
     * order - view, like
     */
    @GetMapping("/{id}/board")
    public CommunityListResult<List<StudyCommunityListResponse>> getList(@PathVariable("id") Long studySequence, @ModelAttribute CommunitySearch condition, HttpSession session) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> communitySearch = {}", condition);
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return studyCommunityService.getCommunities(condition.validateNull(), studySequence, loginSequence);
    }

    @GetMapping("/{id}/board/notices")
    public CommunityListResult<List<StudyCommunityListResponse>> getNotices(@PathVariable("id") Long studySequence, @PageableDefault(size = 20) Pageable pageable, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return studyCommunityService.getNotices(pageable, studySequence, loginSequence);
    }

    @GetMapping("/{id}/board/popular")
    public List<StudyCommunityListResponse> getMostPopular(@PathVariable("id") Long studySequence, @PageableDefault(size = 10) Pageable pageable, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return studyCommunityService.getMostLoveCommunitiesOfWeek(pageable, studySequence, loginSequence);
    }


    /**
     * 커뮤니티 상세 정보를 가져오는 API
     *
     * @param communitySequence PK
     * @return ResponseEntity<CommunityResponse> 200 OK, 커뮤니티 상세 정보
     */
    @GetMapping("/{id}/board/{boardId}")
    public ResponseEntity<StudyCommunityDetailResponse> readCommunityById(@PathVariable("id") Long studySequence, @PathVariable("boardId") Long communitySequence,
                                                                          HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        loginSequence = nullSafeLoginSequence(loginSequence);

        return ResponseEntity.status(HttpStatus.OK).body(studyCommunityService.readStudyCommunityDetail(communitySequence, loginSequence, studySequence));
    }

    /**
     * 커뮤니티 생성 API
     *
     * @param request 생성 정보
     * @return ResponseEntity<CommunityResponse> 201 Created, 생성된 커뮤니티 정보
     */
    @PostMapping("/{id}/board")
    public ResponseEntity<StudyCommunityDetailResponse> createCommunity(@PathVariable("id") Long studySequence, @RequestBody StudyCommunityCreate request, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        MemberEntity findMember = memberService.getMember(loginSequence);

        StudyCommunityDetailResponse response = studyCommunityService.createStudyCommunity(request, findMember, studySequence);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 커뮤니티 수정 API
     *
     * @param communitySequence 커뮤니티 sequence (PK)
     * @param request           수정 정보
     * @return ResponseEntity<UserResponse> 200 OK, 수정된 커뮤니티글 정보
     */
    @PutMapping("/{id}/board/{boardid}")
    public ResponseEntity<StudyCommunityDetailResponse> updateCommunity(@PathVariable("id") Long studySequence, @PathVariable("boardid") Long communitySequence,
                                                                        @RequestBody StudyCommunityEdit request,
                                                                        HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        StudyCommunityDetailResponse response = studyCommunityService.updateCommunity(loginSequence, communitySequence, request, studySequence);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * 커뮤니티 삭제 API
     *
     * @param communitySequence 커뮤니티 sequence (PK)
     * @return ResponseEntity<Void> 204 No Content
     */
    @DeleteMapping("/{id}/board/{boardId}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable("id") Long studySequence, @PathVariable("boardId") long communitySequence, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);

        studyCommunityService.deleteStudyCommunity(loginSequence, communitySequence, studySequence);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // ---------------------------------------------------------------북마크---------------------------------------------------------------

    /**
     * 북마크 생성 API - DONE
     *
     * @param communitySequence
     * @param session
     */
    @PostMapping("/{id}/board/{boardid}/bookmark")
    public ResponseEntity<Void> createBookmark(@PathVariable("id") Long studySequence, @PathVariable("boardid") Long communitySequence,
                                               HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        studyCommunityBookmarkService.createBookmark(loginSequence, communitySequence, studySequence);

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
    @DeleteMapping("/{id}/board/{boardid}/bookmark")
    public ResponseEntity<Void> deleteBookmark(@PathVariable("id") Long studySequence, @PathVariable("boardid") Long communitySequence,
                                               HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        studyCommunityBookmarkService.deleteBookmark(loginSequence, communitySequence, studySequence);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // ---------------------------------------------------------------좋아요---------------------------------------------------------------

    /**
     * 좋아요 생성 API
     */
    @PostMapping("/{id}/board/{boardid}/love")
    public ResponseEntity<Void> createLove(@PathVariable("id") Long studySequence, @PathVariable("boardid") Long communitySequence,
                                           HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        studyCommunityLoveService.createLove(loginSequence, communitySequence, studySequence);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    /**
     * 좋아요 삭제 API
     */
    @DeleteMapping("/{id}/board/{boardid}/love")
    public ResponseEntity<Void> deleteLove(@PathVariable("id") Long studySequence, @PathVariable("boardid") Long communitySequence,
                                           HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        studyCommunityLoveService.deleteLove(loginSequence, communitySequence, studySequence);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

// ---------------------------------------------------------------댓글---------------------------------------------------------------


    /**
     * 커뮤니티 id별로 모든 댓글 정보를 가져오는 API
     *
     * @param id 커뮤니티의 id
     * @return ResponseEntity<List < CommentResponse>> 200 OK, 댓글 정보 목록
     */
    @GetMapping("/{id}/board/{boardid}/comments")
    public ResponseEntity<List<StudyCommunityCommentResponse>> readAllCommentByBoard(@PathVariable("id") Long studySequence, @PathVariable("boardid") Long id, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return ResponseEntity.status(HttpStatus.OK).body(studyCommunityCommentService.readAllComment(id, loginSequence, studySequence));
    }

    /**
     * 댓글 생성 API
     *
     * @param id      커뮤니티의 id
     * @param request 생성 정보 (parentSeq포함)
     * @return ResponseEntity<CommentResponse> 201 Created, 생성된 댓글 정보
     */
    @PostMapping("/{id}/board/{boardid}/comments")
    public ResponseEntity<StudyCommunityCommentResponse> createComment(@PathVariable("id") Long studySequence, @PathVariable("boardid") Long id, @RequestBody StudyCommunityCommentCreate request, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        request.changeMemberSeq(loginSequence);
        request.changecommunitySeq(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(studyCommunityCommentService.createComment(id, request, studySequence));
    }

    /**
     * 댓글 수정 API
     *
     * @param id        커뮤니티의 id
     * @param commentId 댓글의 id
     * @param request   수정 정보
     */
    @PutMapping("/{id}/board/{boardid}/comments/{commentid}")
    public ResponseEntity<Void> updateComment(@PathVariable("id") Long studySequence, @PathVariable("boardid") Long id, @PathVariable("commentid") Long commentId, @RequestBody StudyCommunityCommentEdit request, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        studyCommunityCommentService.UpdateComment(id, commentId, request, loginSequence, studySequence);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * 댓글 삭제 API
     *
     * @param id        커뮤니티의 id
     * @param commentId 댓글의 id
     * @return ResponseEntity<Object> 204 No Content
     */
    @DeleteMapping("/{id}/board/{boardid}/comments/{commentid}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long studySequence, @PathVariable("boardid") Long id, @PathVariable("commentid") Long commentId, HttpSession session) {
        Long loginSequence = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        studyCommunityCommentService.deleteComment(id, commentId, loginSequence, studySequence);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


    private Long nullSafeLoginSequence(Long loginSequence) {
        if (loginSequence == null) {
            return 0L;
        }
        return loginSequence;
    }

}
