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
import com.justudy.backend.study.domain.StudyMemberEntity;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyMemberCreate;
import com.justudy.backend.study.dto.response.StudyResponse;
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

import java.util.List;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class StudyMemberServiceTest {
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
    private StudyMemberService studyMemberService;
    @Autowired
    private StudyRepository repository;
    @Autowired
    private StudyMemberRepository studyMemberRepository;
    @Autowired
    private UploadFileRepository uploadFileRepository;
    private Pageable pageable;
    private MemberEntity findMember;
    private MemberEntity findMember2;
    private CategoryEntity categoryEntity;
    UploadFileEntity basicImage;
    Long studyId;
    CategoryEntity java;

    @Transactional
    @BeforeEach
    public void setUp() {
        log = (Logger) LoggerFactory.getLogger(CommunityRepository.class);
        pageable = PageRequest.of(0, 9);
        log.info("정보1 : start set up->{}", findMember);
        //member
        MemberCreate memberRequest = makeMemberCreate(10011);
        MemberCreate memberRequest2 = makeMemberCreate(10111);
        Long savedMemberId = memberService.saveMember(memberRequest, basicImage);
        Long savedMemberId2 = memberService.saveMember(memberRequest2, basicImage);
        findMember = memberRepository.findById(savedMemberId).get();
        findMember2 = memberRepository.findById(savedMemberId2).get();
        //study
        StudyCreate create = makeRequest(findMember);
        Long studyId = service.createStudy(create, basicImage);
        this.studyId = studyId;
        log.info("정보2 : end set up->{}", findMember.getSequence());
    }

    @Transactional
    @Test
    @Order(1)
    void createStudyMember() {
        // Given
        StudyMemberCreate create = makeMemberRequest(studyId, findMember.getSequence());

        // When
        Long memberId = studyMemberService.createStudyMember(create);
        StudyMemberEntity entity = studyMemberRepository.findById(memberId).get();

        // Then
        Assertions.assertThat(entity.getSequence()).isEqualTo(memberId);
        Assertions.assertThat(entity.getMember().getNickname()).isEqualTo("테스트 봇10011");
        Assertions.assertThat(entity.getStudy().getSequence()).isEqualTo(studyId);
    }

    @Transactional
    @Test
    @Order(2)
    void readAllRegisterStudy() {
        // Given
        StudyMemberCreate create = makeMemberRequest(studyId, findMember.getSequence());

        // When
        Long memberId = studyMemberService.createStudyMember(create);

        StudyMemberEntity entity2 = studyMemberRepository.findById(memberId).get();
//        studyEntity.addStudyMember(studyMemberRepository.findById(memberId).get());
//        StudyMemberEntity entity1 = studyMemberRepository.findById(memberId).get();


        StudyEntity studyEntity = repository.findById(studyId).get();


        log.info("정보3 ->{}, {}, {}, {}, {}", findMember.getSequence(), studyId,studyEntity.getSequence(),service.readStudy(studyId).getSequence() ,entity2.getSequence());
        List<StudyResponse> entity = studyMemberService.readAllRegisterStudy(findMember.getSequence());

//         Then
        Assertions.assertThat(entity.size()).isEqualTo(1);
        Assertions.assertThat(entity.get(0).getName()).isEqualTo("test study");
    }

    @Transactional
    @Test
    @Order(3)
    void updateStudyLeader() {
        // Given
        StudyMemberCreate create = makeMemberRequest(studyId, findMember.getSequence());
        StudyMemberCreate create2 = makeMemberRequest(studyId, findMember2.getSequence());

        // When
        Long memberId = studyMemberService.createStudyMember(create);
        Long memberId2 = studyMemberService.createStudyMember(create2);
        StudyMemberEntity entity = studyMemberRepository.findById(memberId).get();
        StudyMemberEntity entity2 = studyMemberRepository.findById(memberId2).get();

        studyMemberService.updateStudyLeader(studyId, findMember2.getSequence());

        StudyEntity studyEntity = repository.findById(studyId).get();

        // Then
        Assertions.assertThat(studyEntity.getLeaderSeq()).isEqualTo(findMember2.getSequence());
    }


    @Transactional
    @Test
    @Order(4)
    void deleteStudyMemberByStudy() {
        // Given
        StudyMemberCreate create = makeMemberRequest(studyId, findMember.getSequence());
        StudyMemberCreate create2 = makeMemberRequest(studyId, findMember2.getSequence());

        // When
        Long memberId = studyMemberService.createStudyMember(create);
        Long memberId2 = studyMemberService.createStudyMember(create2);
        StudyMemberEntity entity = studyMemberRepository.findById(memberId).get();
        StudyMemberEntity entity2 = studyMemberRepository.findById(memberId2).get();

        StudyEntity studyEntity = repository.findById(studyId).get();
        studyMemberService.deleteStudyMemberByStudy(studyId);


        // Then
        Assertions.assertThat(studyEntity.getStudyMembers().size()).isEqualTo(0);
    }

    @Transactional
    @Test
    @Order(5)
    void exileStudyMember() {
        // Given
        StudyMemberCreate create = makeMemberRequest(studyId, findMember.getSequence());
        StudyMemberCreate create2 = makeMemberRequest(studyId, findMember2.getSequence());

        // When
        Long memberId = studyMemberService.createStudyMember(create);
        Long memberId2 = studyMemberService.createStudyMember(create2);
        StudyMemberEntity entity = studyMemberRepository.findById(memberId).get();
        StudyMemberEntity entity2 = studyMemberRepository.findById(memberId2).get();

        log.info("정보6 {},{}", studyId,findMember2.getSequence());
        StudyEntity studyEntity = repository.findById(studyId).get();

        studyMemberService.exileStudyMember(studyId, findMember2.getSequence());


        StudyEntity studyEntity2 = repository.findById(studyId).get();

        // Then
        Assertions.assertThat(studyEntity2.getStudyMembers().size()).isEqualTo(1);
        Assertions.assertThat(studyEntity2.getStudyMembers().get(0).getSequence()).isEqualTo(memberId);
    }

    @Transactional
    @Test
    @Order(6)
    void withdrawStudyMember() {
        // Given
        StudyMemberCreate create = makeMemberRequest(studyId, findMember.getSequence());
        StudyMemberCreate create2 = makeMemberRequest(studyId, findMember2.getSequence());

        // When
        Long memberId = studyMemberService.createStudyMember(create);
        Long memberId2 = studyMemberService.createStudyMember(create2);
        StudyMemberEntity entity = studyMemberRepository.findById(memberId).get();
        StudyMemberEntity entity2 = studyMemberRepository.findById(memberId2).get();

        StudyEntity studyEntity = repository.findById(studyId).get();
        studyMemberService.withdrawStudyMember(studyId,findMember2.getSequence());


        // Then
        Assertions.assertThat(studyEntity.getStudyMembers().size()).isEqualTo(1);
    }

    private StudyMemberCreate makeMemberRequest(Long study, Long member) {
        return StudyMemberCreate
                .builder()
                .studySeq(study)
                .memberSeq(member)
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