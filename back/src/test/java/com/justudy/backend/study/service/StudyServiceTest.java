package com.justudy.backend.study.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.member.service.MemberService;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyEdit;
import com.justudy.backend.study.dto.response.StudyResponse;
import com.justudy.backend.study.repository.StudyRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class StudyServiceTest {

    Logger log;
    @Autowired
    private StudyRepository repository;
    @Autowired
    private StudyService service;
    private Pageable pageable;

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CategoryRepository categoryRepository;

    private final String USER_ID = "justudy";
    private final String NICKNAME = "levi";
    private final String SSAFY_ID = "0847968";
    private MemberEntity findMember;
    Long id;
    CategoryEntity java;

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
        MemberCreate memberRequest = makeMemberCreateBuilder()
                .userId(USER_ID)
                .nickname(NICKNAME)
                .ssafyId(SSAFY_ID)
                .build();
        Long savedMemberId = memberService.saveMember(memberRequest, null);
        findMember = memberRepository.findById(savedMemberId).get();

        CategoryEntity backend = createMainCategory("backend", 0L);
        categoryRepository.save(backend);
        java = createSubCategory("Java", 1L, backend);
        categoryRepository.save(java);
        log.info("정보2 : end set up->{}", findMember.getSequence());
    }

    @Transactional
    @Test
    @Order(1)
    void createStudy() {
        // Given
        StudyCreate create = makeRequest(findMember);

        // When
        Long studyId = service.createStudy(create);
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
        Long id = service.createStudy(create);
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
        Long id = service.createStudy(create);
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
        Long studyId = service.createStudy(create);

        List<String> sub = new ArrayList<>();
        sub.add("Figma");
        sub.add("Java");

        // When
        Slice<StudyResponse> study = service.search(0, sub, "", "");

        // Then

        log.info("slice info : {}", study.toString());
//        Assertions.assertThat(study.hasNext()).isEqualTo(true);
        Assertions.assertThat(study.get().collect(Collectors.toList()).size()).isEqualTo(1);
        Assertions.assertThat(study.get().collect(Collectors.toList()).get(0).getCategory().getName()).isEqualTo("Java");
//        Assertions.assertThat(repository.findAll().size()).isEqualTo(2);
    }


    @Transactional
    @Test
    @Order(5)
    void deleteStudy() {
        // Given
        StudyCreate create = makeRequest(findMember);

        // When
        Long id = service.createStudy(create);
        service.deleteStudy(id);
        // Then

        Assertions.assertThat(repository.findAll().size()).isEqualTo(0);
    }

    private StudyCreate makeRequest(MemberEntity findMember) {
        return StudyCreate
                .builder()
                .frequencies(null)
                .category(java)
                .name("test study")
                .leaderSeq(findMember.getSequence())
                .introduction("소개입니당")
                .personnel(10)
                .level("초보")
                .onlineOffline("온라인")
                .isOpen(true)
                .github("git")
                .notion("notiono")
                .startTime("230202")
                .build();
    }

    private StudyEdit makeRequest(Long id, MemberEntity findMember) {
        return StudyEdit
                .builder()
                .sequence(id)
                .studyMembers(null)
                .resumes(null)
                .frequencies(null)
                .category(java)
                .name("test study2")
                .leaderSeq(findMember.getSequence())
                .introduction("소개입니당2")
                .personnel(10)
                .level("초보")
                .onlineOffline("온라인")
                .isOpen(true)
                .github("git")
                .notion("notiono")
                .startTime("220202")
                .build();
    }

    private MemberCreate.MemberCreateBuilder makeMemberCreateBuilder() {
        return MemberCreate.builder()
                .password("1234")
                .passwordCheck("1234")
                .username("이싸피")
                .phone("01051391111")
                .email("ssafylee@ssafy.com")
                .region("SEOUL")
                .dream("백엔드취업 희망")
                .category(new String[]{"JAVA", "Spring", "JPA"})
                .introduction("test.");
    }

    private CategoryEntity createSubCategory(String name, Long level, CategoryEntity parent) {
        CategoryEntity subCategory = CategoryEntity.builder()
                .name(name)
                .categoryLevel(level)
                .build();
        subCategory.addParentCategory(parent);
        return subCategory;
    }

    private CategoryEntity createMainCategory(String name, Long level) {
        return CategoryEntity.builder()
                .name(name)
                .categoryLevel(level)
                .build();
    }
}