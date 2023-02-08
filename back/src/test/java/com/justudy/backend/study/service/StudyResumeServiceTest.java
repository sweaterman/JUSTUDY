package com.justudy.backend.study.service;

import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.repository.UploadFileRepository;
import com.justudy.backend.file.service.UploadFileService;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.member.service.MemberService;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.StudyMemberEntity;
import com.justudy.backend.study.domain.StudyResumeEntity;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyMemberCreate;
import com.justudy.backend.study.dto.request.StudyResumeCreate;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.dto.response.StudyResumeResponse;
import com.justudy.backend.study.repository.StudyMemberRepository;
import com.justudy.backend.study.repository.StudyRepository;
import com.justudy.backend.study.repository.StudyResumeRepository;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class StudyResumeServiceTest {
    Logger log;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    private StudyService service;
    @Autowired
    private StudyResumeService studyResumeService;
    @Autowired
    private StudyRepository repository;
    @Autowired
    private StudyResumeRepository studyResumeRepository;
    @Autowired
    private StudyMemberRepository studyMemberRepository;
    @Autowired
    private StudyMemberService studyMemberService;
    @Autowired
    private UploadFileRepository uploadFileRepository;
    private Pageable pageable;
    private MemberEntity findMember;
    private MemberEntity findMember2;
    UploadFileEntity basicImage;
    Long id;

    @Transactional
    @BeforeEach
    public void setUp() {
        log = (Logger) LoggerFactory.getLogger(CommunityRepository.class);
        pageable = PageRequest.of(0, 9);
        log.info("정보1 : start set up->{}", findMember);
        //image
        UploadFileEntity basicMemberImage = new UploadFileEntity("basic_member.png", "basic_member.png");
        uploadFileRepository.save(basicMemberImage);
//        basicImage = uploadFileService.getUploadFile(ImageConst.BASIC_MEMBER_IMAGE);//
//        UploadFileEntity basicImage = uploadFileRepository.findById(ImageConst.BASIC_MEMBER_IMAGE)
//                .orElseThrow(UploadFileNotFound::new);
        //member
        MemberCreate memberRequest = makeMemberCreate(100);
        MemberCreate memberRequest2 = makeMemberCreate(101);
        Long savedMemberId = memberService.saveMember(memberRequest, basicImage);
        Long savedMemberId2 = memberService.saveMember(memberRequest2, basicImage);
        findMember = memberRepository.findById(savedMemberId).get();
        findMember2 = memberRepository.findById(savedMemberId2).get();

        //category
//        CategoryEntity backend = createMainCategory("backend", 0L);
//        categoryRepository.save(backend);
//        java = createSubCategory("Java", 1L, backend);
//        categoryRepository.save(java);

        //study
        StudyCreate create = makeRequest(findMember);
        Long studyId = service.createStudy(create, basicImage);
        id = studyId;
        log.info("정보2 : end set up->{}", findMember.getSequence());
    }


    @Transactional
    @Test
    @Order(1)
    void createStudyResume() {
        // Given
        StudyResumeCreate create = makeResumeRequest(id, findMember.getSequence());

        // When
        Long resumeId = studyResumeService.createStudyResume(create);
        StudyResumeEntity entity = studyResumeRepository.findById(resumeId).get();

        // Then
        org.assertj.core.api.Assertions.assertThat(entity.getSequence()).isEqualTo(resumeId);
        org.assertj.core.api.Assertions.assertThat(entity.getContent()).isEqualTo("content입니다");
    }

    @Transactional
    @Test
    @Order(2)
    void readStudyResume() {
        // Given
        StudyResumeCreate create = makeResumeRequest(id, findMember.getSequence());

        // When
        Long resumeId = studyResumeService.createStudyResume(create);
//        StudyResumeEntity entity = studyResumeRepository.findById(resumeId).get();

        StudyResumeResponse entity = studyResumeService.readStudyResume(resumeId);


        // Then
        org.assertj.core.api.Assertions.assertThat(entity.getSequence()).isEqualTo(resumeId);
        org.assertj.core.api.Assertions.assertThat(entity.getContent()).isEqualTo("content입니다");
    }


    @Transactional
    @Test
    @Order(3)
    void deleteStudyResume() {
        // Given
        StudyResumeCreate create = makeResumeRequest(id, findMember.getSequence());
        StudyResumeCreate create2 = makeResumeRequest(id, findMember2.getSequence());

        // When
        Long resumeId = studyResumeService.createStudyResume(create);
        Long resumeId2 = studyResumeService.createStudyResume(create2);

//        studyResumeService.deleteStudyResume(resumeId, loginSequence);
//        studyResumeService.deleteStudyResume(resumeId2, loginSequence);
//        List<StudyResumeResponse> entity = studyResumeService.readAllStudyResumeByStudy(id, loginSequence);

        // Then
//        org.assertj.core.api.Assertions.assertThat(entity.size()).isEqualTo(0);
    }

    @Transactional
    @Test
    @Order(4)
    void readAllStudyResumeByStudy() {
        // Given
        StudyResumeCreate create = makeResumeRequest(id, findMember.getSequence());
        StudyResumeCreate create2 = makeResumeRequest(id, findMember2.getSequence());


        // When
        Long resumeId = studyResumeService.createStudyResume(create);
        Long resumeId2 = studyResumeService.createStudyResume(create2);
        StudyEntity studyEntity = repository.findById(id).get();
//        studyEntity.addResume(studyResumeRepository.findById(resumeId).get());
//        studyEntity.addResume(studyResumeRepository.findById(resumeId2).get());
//        StudyResumeEntity entity = studyResumeRepository.findById(resumeId).get();
//        StudyResumeEntity entity2 = studyResumeRepository.findById(resumeId2).get();

        log.info("정보3 : start->{}");
//        List<StudyResumeResponse> entity = studyResumeService.readAllStudyResumeByStudy(id, loginSequence);

        log.info("정보3 : end->{}");
        // Then
//        org.assertj.core.api.Assertions.assertThat(entity.size()).isEqualTo(2);
    }

    @Transactional
    @Test
    @Order(5)
    void readAllApplyStudy() {
        // Given
        StudyResumeCreate create = makeResumeRequest(id, findMember.getSequence());
        StudyResumeCreate create2 = makeResumeRequest(id, findMember.getSequence());


        // When
        Long resumeId = studyResumeService.createStudyResume(create);
        Long resumeId2 = studyResumeService.createStudyResume(create2);
        StudyEntity studyEntity = repository.findById(id).get();
        studyEntity.addStudyResume(studyResumeRepository.findById(resumeId).get());
        studyEntity.addStudyResume(studyResumeRepository.findById(resumeId2).get());
        StudyResumeEntity entity3 = studyResumeRepository.findById(resumeId).get();
        StudyResumeEntity entity2 = studyResumeRepository.findById(resumeId2).get();
        Long memberid = studyMemberService.createStudyMember(StudyMemberCreate
                .builder()
                .studySeq(id)
                .memberSeq(findMember.getSequence())
                .build());
        StudyMemberEntity studyMemberEntity = studyMemberRepository.findById(memberid).get();
        studyEntity.addStudyMember(studyMemberEntity);

        List<StudyResponse> entity = studyResumeService.readAllApplyStudy(findMember.getSequence());

        // Then
        org.assertj.core.api.Assertions.assertThat(entity.size()).isEqualTo(2);
    }

    private StudyResumeCreate makeResumeRequest(Long study, Long member) {
        return StudyResumeCreate
                .builder()
                .studySeq(study)
                .memberSeq(member)
                .content("content입니다")
                .build();
    }


    private StudyCreate makeRequest(MemberEntity findMember) {
        return StudyCreate
                .builder()
                .frequency(null)
                .bottomCategory("java")
                .name("test study")
                .leaderSeq(findMember.getSequence())
                .leader(findMember.getNickname())
                .introduction("소개입니당")
                .population(10)
                .level("초보")
                .meeting("온라인")
                .github("git")
                .notion("notiono")
                .startTime("230202")
                .build();
    }

    private static MemberCreate makeMemberCreate(int number) {
        MemberCreate request = MemberCreate.builder()
                .userId("test" + number)
                .password("1234")
                .passwordCheck("1234")
                .username("테스트" + number)
                .nickname("테스트 봇" + number)
                .ssafyId("08" + number)
                .phone(String.valueOf(number))
                .email("testEmail" + number + "@ssafy.com")
                .region("SEOUL")
                .category(new String[]{"java", "Spring"})
                .introduction("테스트 봇" + number + " 입니다.")
                .build();
        return request;
    }

}