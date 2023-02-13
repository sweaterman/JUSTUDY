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
import com.justudy.backend.study.domain.StudyFrequencyEntity;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyEdit;
import com.justudy.backend.study.dto.request.StudyFrequencyCreate;
import com.justudy.backend.study.dto.response.StudyFrequencyResponse;
import com.justudy.backend.study.repository.StudyFrequencyRepository;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class StudyFrequencyServiceTest {

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
    private StudyFrequencyService studyFrequencyService;
    @Autowired
    private StudyRepository repository;
    @Autowired
    private StudyFrequencyRepository studyFrequencyRepository;
    @Autowired
    private UploadFileRepository uploadFileRepository;
    private Pageable pageable;
    private MemberEntity findMember;
    private CategoryEntity categoryEntity;
    UploadFileEntity basicImage;
    Long id;
    CategoryEntity java;

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
        MemberCreate memberRequest = makeMemberCreate(999);
        Long savedMemberId = memberService.saveMember(memberRequest, basicImage);
        findMember = memberRepository.findById(savedMemberId).get();

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
    void createStudyFrequency() throws ParseException {
        // Given
        StudyFrequencyCreate create = makeFrequencyRequest(id);

        SimpleDateFormat formatter = new SimpleDateFormat("HH시 mm분");
        Date date = formatter.parse("18시 00분");

        // When
        Long frequencyId = studyFrequencyService.createStudyFrequency(id, create);
        StudyFrequencyEntity entity = studyFrequencyRepository.findById(frequencyId).get();

        // Then
        Assertions.assertThat(entity.getSequence()).isEqualTo(frequencyId);
        Assertions.assertThat(entity.getWeek()).isEqualTo("월");
        Assertions.assertThat(entity.getStartTime()).isEqualTo(date);
        Assertions.assertThat(entity.getEndTime()).isEqualTo(date);
    }

    @Transactional
    @Test
    @Order(2)
    void readAllStudyFrequency() throws ParseException {
        // Given
        SimpleDateFormat formatter = new SimpleDateFormat("HH시 mm분");
        Date date = formatter.parse("18시 00분");

        StudyFrequencyCreate[] create = new StudyFrequencyCreate[3];
        Long[] freId = new Long[3];
        for (int i = 0; i < 3; i++) {
            create[i] = makeFrequencyRequest(id);
        }

        // When
        for (int i = 0; i < 3; i++) {
            freId[i] = studyFrequencyService.createStudyFrequency(id, create[i]);
        }
        List<StudyFrequencyResponse> result = studyFrequencyService.readAllStudyFrequency(id);

        // Then
        for (int i = 0; i < 3; i++) {
//            Assertions.assertThat(result.get(i).getSequence()).isEqualTo(freId[i]);
            Assertions.assertThat(result.get(i).getWeek()).isEqualTo("월");
            Assertions.assertThat(result.get(i).getStartTime()).isEqualTo(date);
            Assertions.assertThat(result.get(i).getEndTime()).isEqualTo(date);
        }
    }

//    @Transactional
//    @Test
//    @Order(3)
//    void updateStudyFrequency() throws ParseException {
////        SimpleDateFormat formatter = new SimpleDateFormat("HH시 mm분");
////        Date date = formatter.parse("18:00");
////
////        StudyFrequencyCreate create = makeFrequencyRequest(id);
////        Long frequencyId = studyFrequencyService.createStudyFrequency(id, create);
////
////        StudyFrequencyEdit edit = makeFrequencyEditRequest(id);
////
////        // When
////        studyFrequencyService.updateStudyFrequency(id,edit);
////
////        // Then
////        Assertions.assertThat(entity.getSequence()).isEqualTo(frequencyId);
////        Assertions.assertThat(entity.getWeek()).isEqualTo("월");
////        Assertions.assertThat(entity.getStartTime()).isEqualTo(date);
////        Assertions.assertThat(entity.getEndTime()).isEqualTo(date);
//    }

    @Transactional
    @Test
    @Order(4)
    void deleteStudyFrequency() throws ParseException {
        // Given
        StudyFrequencyCreate create = makeFrequencyRequest(id);

        SimpleDateFormat formatter = new SimpleDateFormat("HH시 mm분");
        Date date = formatter.parse("18시 00분");

        // When
        Long frequencyId = studyFrequencyService.createStudyFrequency(id, create);
        studyFrequencyService.deleteStudyFrequency(id, frequencyId);

        // Then
        Assertions.assertThat(studyFrequencyService.readAllStudyFrequency(id).size()).isEqualTo(0);
    }

    @Transactional
    @Test
    @Order(5)
    void deleteStudyFrequencyByStudy() throws ParseException {
        // Given
        SimpleDateFormat formatter = new SimpleDateFormat("HH시 mm분");
        Date date = formatter.parse("18시 00분");

        StudyFrequencyCreate[] create = new StudyFrequencyCreate[3];
        Long[] freId = new Long[3];
        for (int i = 0; i < 3; i++) {
            create[i] = makeFrequencyRequest(id);
        }

        // When
        StudyEntity studyEntity = repository.findById(id).get();
        for (int i = 0; i < 3; i++) {
            freId[i] = studyFrequencyService.createStudyFrequency(id, create[i]);
            studyEntity.addStudyFrequency(studyFrequencyRepository.findById(freId[i]).get());
        }
        Assertions.assertThat(studyFrequencyService.readAllStudyFrequency(id).size()).isEqualTo(3);
        studyFrequencyService.deleteStudyFrequencyByStudy(id);

        // Then
        Assertions.assertThat(studyFrequencyService.readAllStudyFrequency(id).size()).isEqualTo(0);
        Assertions.assertThat(studyEntity.getFrequency().size()).isEqualTo(0);
    }

//    @Test
//    @Order(6)
//    void readStudyFrequency() {
//    }

    private StudyFrequencyCreate makeFrequencyRequest(Long study) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH시 mm분");
        Date date = formatter.parse("18시 00분");
        return StudyFrequencyCreate
                .builder()
                .studySeq(study)
                .week("월")
                .startTime(date.toString())
                .endTime(date.toString())
                .build();
    }


    private StudyCreate makeRequest(MemberEntity findMember) {
        return StudyCreate
                .builder()
                .frequency(null)
                .bottomCategory("java")
                .name("test study")
//                .leaderSeq(findMember.getSequence())
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

    private StudyEdit makeRequest(Long id, MemberEntity findMember) {
        return StudyEdit
                .builder()
                .sequence(id)
                .frequency(null)
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
                .region("SEOUL")
                .category(new String[]{"java", "Spring"})
                .introduction("테스트 봇" + number + " 입니다.")
                .build();
        return request;
    }

}