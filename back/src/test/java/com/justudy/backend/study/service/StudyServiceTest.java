package com.justudy.backend.study.service;

import com.justudy.backend.category.domain.CategoryEntity;
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
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyEdit;
import com.justudy.backend.study.dto.request.StudyFrequencyCreate;
import com.justudy.backend.study.dto.request.StudyMemberCreate;
import com.justudy.backend.study.dto.response.StudyDetailResponse;
import com.justudy.backend.study.repository.StudyFrequencyRepository;
import com.justudy.backend.study.repository.StudyMemberRepository;
import com.justudy.backend.study.repository.StudyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class StudyServiceTest {

    private final String USER_ID = "justudy";
    private final String NICKNAME = "levi";
    private final String SSAFY_ID = "0847968";
    Logger log;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StudyFrequencyRepository studyFrequencyRepository;
    @Autowired
    StudyFrequencyService studyFrequencyService;
    @Autowired
    StudyMemberRepository studyMemberRepository;
    @Autowired
    StudyMemberService studyMemberService;
    @Autowired
    UploadFileService uploadFileService;
    Long id;
    CategoryEntity java;
    UploadFileEntity basicImage;
    @Autowired
    private StudyRepository repository;
    @Autowired
    private UploadFileRepository uploadFileRepository;
    @Autowired
    private StudyService service;
    private Pageable pageable;
    private MemberEntity findMember;
    private CategoryEntity categoryEntity;


    @Transactional
    @BeforeEach
    public void setUp() {
        log = (Logger) LoggerFactory.getLogger(CommunityRepository.class);
        pageable = PageRequest.of(0, 9);
        log.info("정보1 : start set up->{}", findMember);
        MemberCreate memberRequest = makeMemberCreate(100);
        Long savedMemberId = memberService.saveMember(memberRequest, basicImage);
        findMember = memberRepository.findById(savedMemberId).get();


        log.info("정보2 : end set up->{}", findMember.getSequence());
    }

    @Transactional
    @Test
    @Order(1)
    void createStudy() {
        // Given
        StudyCreate create = makeRequest(findMember);
        // When
        Long studyId = service.createStudy(create, basicImage);
        StudyEntity study = repository.findById(studyId).get();

        // Then

        Assertions.assertThat(study.getName()).isEqualTo("test study");
        Assertions.assertThat(study.getIntroduction()).isEqualTo("소개입니당");
        Assertions.assertThat(study.getStartTime()).isEqualTo("230202");
        Assertions.assertThat(study.getLeaderSeq()).isEqualTo(findMember.getSequence());
    }

    @Transactional
    @Test
    @Order(2)
    void readStudy() {
        // Given
        StudyCreate create = makeRequest(findMember);

        // When
        Long id = service.createStudy(create, basicImage);
        StudyDetailResponse study = service.readStudy(id);

        // Then

        log.info("정보3 : {}", study.getLeaderSeq());
        log.info("정보3 : {}", study.getName());
        log.info("정보3 : {}", study.getIntroduction());
        log.info("정보3 : {}", study.getStartTime());
        Assertions.assertThat(study.getLeaderSeq()).isEqualTo(findMember.getSequence());
        Assertions.assertThat(study.getName()).isEqualTo("test study");
        Assertions.assertThat(study.getIntroduction()).isEqualTo("소개입니당");
        Assertions.assertThat(study.getStartTime()).isEqualTo("230202");
    }

    @Transactional
    @Test
    @Order(3)
    void updateStudy() {
        // Given
        StudyCreate create = makeRequest(findMember);
        Long id = service.createStudy(create, basicImage);
        List<StudyFrequencyCreate> studyFrequencycreates = new ArrayList<>();
        studyFrequencycreates.add(StudyFrequencyCreate.builder().studySeq(id).week("월").build());
        studyFrequencycreates.add(StudyFrequencyCreate.builder().studySeq(id).week("화").build());
        studyFrequencycreates.add(StudyFrequencyCreate.builder().studySeq(id).week("수").build());
        StudyEdit edit = makeRequest(id, findMember, studyFrequencycreates);

        // When
        Long studyId = service.updateStudy(id, edit, null);
//        Long studyId = service.updateStudy(id, edit, uploadImage);
        StudyEntity study = repository.findById(studyId).get();

        // Then

        Assertions.assertThat(study.getLeaderSeq()).isEqualTo(findMember.getSequence());
        Assertions.assertThat(study.getName()).isEqualTo("test study2");
        Assertions.assertThat(study.getIntroduction()).isEqualTo("소개입니당2");
        Assertions.assertThat(study.getStartTime()).isEqualTo("220202");
    }

    @Transactional
    @Test
    @Order(4)
    void search() {
        // Given
        StudyCreate create = makeRequest(findMember);
        Long studyId = service.createStudy(create, basicImage);
        StudyEntity studyEntity = repository.findById(studyId).get();
        StudyMemberCreate createMember = makeMemberRequest(findMember, studyId);
        studyMemberService.createStudyMember(createMember);
        List<String> sub = new ArrayList<>();
        sub.add("Figma");
        sub.add("Java");

        // When
        log.info("슬라이스1 info : {}", studyEntity.getSequence());
        log.info("슬라이스1 info : {}", studyEntity.getStudyMembers().get(0).getSequence());
        log.info("슬라이스1 info : {}", findMember.getSequence());

//        StudySearchResponse study = service.search(0, null, "", "");
//        StudySearchResponse study = service.search(0, sub, "", "");

        // Then

//        Assertions.assertThat(study.getCheckMore()).isEqualTo(true);
//        Assertions.assertThat(study.getStudyResponse().size()).isEqualTo(1);
//        Assertions.assertThat(study.getStudyResponse().get(0).getBottomCategory()).isEqualTo("Java");
        Assertions.assertThat(repository.findAll().size()).isEqualTo(2);
    }

    @Transactional
    @Test
    @Order(5)
    void deleteStudy() {
        // Given
        StudyCreate create = makeRequest(findMember);

        // When
        Long id = service.createStudy(create, basicImage);
        service.deleteStudy(id);

        // Then

        Assertions.assertThat(repository.findById(id)).isEqualTo(Optional.empty());
    }

    @Transactional
    @Test
    @Order(6)
    void readDetailStudy() {
        // Given
        StudyCreate create = makeRequest(findMember);

        // When
        Long id = service.createStudy(create, basicImage);
//        StudyDetailResponse study = service.readDetailStudy(id, loginSequence);

        // Then
//        log.info("정보3 : {}", study.getLeaderSeq());
//        log.info("정보3 : {}", study.getName());
//        log.info("정보3 : {}", study.getIntroduction());
//        log.info("정보3 : {}", study.getStartTime());
//        Assertions.assertThat(study.getLeaderSeq()).isEqualTo(findMember.getSequence());
//        Assertions.assertThat(study.getName()).isEqualTo("test study");
//        Assertions.assertThat(study.getIntroduction()).isEqualTo("소개입니당");
//        Assertions.assertThat(study.getMember().size()).isEqualTo(0);
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

    private StudyEdit makeRequest(Long id, MemberEntity findMember, List<StudyFrequencyCreate> studyFrequencyCreates) {
        return StudyEdit
                .builder()
                .sequence(id)
                .frequency(studyFrequencyCreates)
                .bottomCategory("java")
                .name("test study2")
//                .leaderSeq(findMember.getSequence())
                .introduction("소개입니당2")
                .population(10)
                .level("초보")
                .meeting("온라인")
                .isOpen(true)
                .github("git")
                .notion("notiono")
                .startTime("220202")
                .build();
    }

    private static MemberCreate makeMemberCreate(int number) {
        MemberCreate request = MemberCreate.builder()
                .userId("test" + number)
                .password("1234")
                .passwordCheck("1234")
                .username("테스트" + number)
                .nickname("테테" + number)
                .ssafyId("08" + number)
                .phone(String.valueOf(number))
                .email("testEmail" + number + "@ssafy.com")
                .region("SEOUL")
                .category(new String[]{"java", "Spring"})
                .introduction("테스트 봇" + number + " 입니다.")
                .build();
        return request;
    }

    private StudyMemberCreate makeMemberRequest(MemberEntity findMember, Long studyId) {
        return StudyMemberCreate
                .builder()
                .memberSeq(findMember.getSequence())
                .studySeq(studyId)
                .build();
    }

}