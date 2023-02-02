package com.justudy.backend.study.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.exception.UploadFileNotFound;
import com.justudy.backend.file.infra.ImageConst;
import com.justudy.backend.file.repository.UploadFileRepository;
import com.justudy.backend.file.service.UploadFileService;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.member.service.MemberService;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyEdit;
import com.justudy.backend.study.dto.response.StudyDetailResponse;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.dto.response.StudySearchResponse;
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
    UploadFileService uploadFileService;
    Long id;
    CategoryEntity java;
    @Autowired
    private StudyRepository repository;
    @Autowired
    private UploadFileRepository uploadFileRepository;
    @Autowired
    private StudyService service;
    private Pageable pageable;
    private MemberEntity findMember;
    private CategoryEntity categoryEntity;
    UploadFileEntity basicImage;

    StudyServiceTest() {
    }

    @Transactional
    @BeforeEach
//    @Test
//    @Order(0)
    public void setUp() {
        log = (Logger) LoggerFactory.getLogger(CommunityRepository.class);
        pageable = PageRequest.of(0, 9);
        log.info("정보1 : start set up->{}", findMember);
        UploadFileEntity basicMemberImage = new UploadFileEntity("basic_member.png", "basic_member.png");
        uploadFileRepository.save(basicMemberImage);
//        basicImage = uploadFileService.getUploadFile(ImageConst.BASIC_MEMBER_IMAGE);//
//        UploadFileEntity basicImage = uploadFileRepository.findById(ImageConst.BASIC_MEMBER_IMAGE)
//                .orElseThrow(UploadFileNotFound::new);

        MemberCreate memberRequest = makeMemberCreate(100);
        Long savedMemberId = memberService.saveMember(memberRequest, basicImage);
        findMember = memberRepository.findById(savedMemberId).get();

//        CategoryEntity backend = createMainCategory("backend", 0L);
//        categoryRepository.save(backend);
//        java = createSubCategory("Java", 1L, backend);
//        categoryRepository.save(java);


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
        id = studyId;
        StudyEntity study = repository.findById(studyId).get();

        // Then

        Assertions.assertThat(study.getLeaderSeq()).isEqualTo(findMember.getSequence());
        Assertions.assertThat(study.getName()).isEqualTo("test study");
        Assertions.assertThat(study.getIntroduction()).isEqualTo("소개입니당");
        Assertions.assertThat(study.getStartTime()).isEqualTo("230202");
    }

    @Test
    @Order(2)
    @Transactional
    void readStudy() {
        // Given
        StudyCreate create = makeRequest(findMember);

        // When
        Long id = service.createStudy(create, basicImage);
        StudyResponse study = service.readStudy(id);

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
        StudyEdit edit = makeRequest(id, findMember);
        StudyCreate create = makeRequest(findMember);

        // When
        Long id = service.createStudy(create, basicImage);
        Long studyId = service.updateStudy(id, edit);
        StudyEntity study = repository.findById(studyId).get();

        // Then

        Assertions.assertThat(study.getLeaderSeq()).isEqualTo(findMember.getSequence());
        Assertions.assertThat(study.getName()).isEqualTo("test study2");
        Assertions.assertThat(study.getIntroduction()).isEqualTo("소개입니당2");
        Assertions.assertThat(study.getStartTime()).isEqualTo("220202");
    }

    @Test
    @Transactional
    @Order(4)
    void search() {
        // Given
        StudyCreate create = makeRequest(findMember);
        Long studyId = service.createStudy(create, basicImage);

        List<String> sub = new ArrayList<>();
        sub.add("Figma");
        sub.add("Java");

        // When
        StudySearchResponse study = service.search(0, sub, "", "");

        // Then

        log.info("slice info : {}", study.toString());
        Assertions.assertThat(study.getCheckMore()).isEqualTo(true);
        Assertions.assertThat(study.getStudyResponse().size()).isEqualTo(1);
        Assertions.assertThat(study.getStudyResponse().get(0).getBottomCategory()).isEqualTo("Java");
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

        Assertions.assertThat(repository.findAll().size()).isEqualTo(0);
    }

    @Test
    @Order(6)
    @Transactional
    void readDetailStudy() {
        // Given
        StudyCreate create = makeRequest(findMember);

        // When
        Long id = service.createStudy(create, basicImage);
        StudyDetailResponse study = service.readDetailStudy(id);

        // Then

        log.info("정보3 : {}", study.getLeaderSeq());
        log.info("정보3 : {}", study.getName());
        log.info("정보3 : {}", study.getIntroduction());
        log.info("정보3 : {}", study.getStartTime());
        Assertions.assertThat(study.getLeaderSeq()).isEqualTo(findMember.getSequence());
        Assertions.assertThat(study.getName()).isEqualTo("test study");
        Assertions.assertThat(study.getIntroduction()).isEqualTo("소개입니당");
        Assertions.assertThat(study.getMember().size()).isEqualTo(1);
    }


    private StudyCreate makeRequest(MemberEntity findMember) {
        return StudyCreate
                .builder()
                .frequency(null)
                .bottomCategory("java")
                .name("test study")
                .leaderSeq(findMember.getSequence())
                .leaderName(findMember.getNickname())
                .introduction("소개입니당")
                .population(10)
                .level("초보")
                .meeting("온라인")
                .github("git")
                .notion("notiono")
                .startTime("230202")
                .build();
    }

    private StudyEdit makeRequest(Long id, MemberEntity findMember) {
        return StudyEdit
                .builder()
                .sequence(id)
                .member(null)
                .resume(null)
                .frequency(null)
                .bottomCategory("java")
                .name("test study2")
                .leaderSeq(findMember.getSequence())
                .introduction("소개입니당2")
                .population(10)
                .level("초보")
                .onlineOffline("온라인")
                .isOpen(true)
                .github("git")
                .notion("notiono")
                .startTime("220202")
                .build();
    }


    private CategoryEntity createSubCategory(String name, Long level, CategoryEntity parent) {
        CategoryEntity subCategory = CategoryEntity.builder()
                .key(name)
                .categoryLevel(level)
                .build();
        subCategory.addParentCategory(parent);
        return subCategory;
    }

    private CategoryEntity createMainCategory(String name, Long level) {
        return CategoryEntity.builder()
                .key(name)
                .categoryLevel(level)
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
                .mmId(number + "test")
                .region("SEOUL")
                .category(new String[]{"java", "Spring"})
                .introduction("테스트 봇" + number + " 입니다.")
                .build();
        return request;
    }
}