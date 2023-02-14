package com.justudy.backend;

import com.justudy.backend.GroupCall.service.StudyRoomService;
import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.service.CommunityService;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.exception.UploadFileNotFound;
import com.justudy.backend.file.infra.ImageConst;
import com.justudy.backend.file.repository.UploadFileRepository;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.member.service.MemberService;
import com.justudy.backend.rank.controller.RankDummy;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.dto.request.StudyFrequencyCreate;
import com.justudy.backend.study.dto.request.StudyMemberCreate;
import com.justudy.backend.study.dto.request.StudyResumeCreate;
import com.justudy.backend.study.service.StudyFrequencyService;
import com.justudy.backend.study.service.StudyMemberService;
import com.justudy.backend.study.service.StudyResumeService;
import com.justudy.backend.study.service.StudyService;
import com.justudy.backend.timer.dto.request.ActivityRequest;
import com.justudy.backend.timer.service.MemberActivityService;
import com.justudy.backend.timer.service.RoomActivityService;

import java.sql.Date;
import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequiredArgsConstructor
@Component
public class InitDb {

    private final InitService initService;

    private static MemberCreate makeMemberCreate(int number, String[] category) {
        MemberCreate request = MemberCreate.builder()
                .userId("test" + number)
                .password("1234")
                .passwordCheck("1234")
                .username("테스트" + number)
                .nickname("봇" + number)
                .region("SEOUL")
                .category(category)
                .ssafyId("08" + number)
                .mmId("mmTest" + number)
                .phone(String.valueOf(number))
                .email("testEmail" + number + "@ssafy.com")
                .build();
        return request;
    }

    @PostConstruct
    public void init() throws ParseException {
        initService.init();
    }

    @RequiredArgsConstructor
    @Component
    @Transactional
    static class InitService {

        private final CategoryRepository categoryRepository;

        private final UploadFileRepository uploadFileRepository;

        private final MemberService memberService;

        private final CommunityService communityService;
        private final StudyService studyService;
        private final StudyFrequencyService studyFrequencyService;
        private final StudyMemberService studyMemberService;
        private final StudyResumeService studyResumeService;

        private final MemberActivityService memberActivityService;
        private final StudyRoomService studyRoomService;
        private final RoomActivityService roomActivityService;
        private final RankDummy rankDummy;


        public void init() throws ParseException {
            saveImageFile();
            saveCategory();
            saveMember();
            saveCommunity();
            saveStudy();
            saveStudyFrequency();
            saveStudyMember();
            saveStudyRoom();
            saveTimer();
            saveRank();
            saveTest1();
            saveAdmin();
        }

        private void saveAdmin() {
            UploadFileEntity basicImage = uploadFileRepository.findById(ImageConst.BASIC_MEMBER_IMAGE)
                    .orElseThrow(UploadFileNotFound::new);
            MemberCreate request = MemberCreate.builder()
                    .userId("consultant")
                    .password("12341234")
                    .passwordCheck("12341234")
                    .username("컨설턴트")
                    .nickname("컨설턴트계정")
                    .region("SEOUL")
                    .category(new String[]{"Java"})
                    .ssafyId("9999999")
                    .mmId("mmTest")
                    .phone(String.valueOf("9999999"))
                    .email("testEmail@ssafy.com")
                    .build();
            memberService.saveAdmin(request, basicImage);

            request = MemberCreate.builder()
                    .userId("coach")
                    .password("12341234")
                    .passwordCheck("12341234")
                    .username("코치")
                    .nickname("코치계정")
                    .region("SEOUL")
                    .category(new String[]{"Java"})
                    .ssafyId("9999998")
                    .mmId("mmTest")
                    .phone(String.valueOf("9999999"))
                    .email("testEmail@ssafy.com")
                    .build();
            memberService.saveAdmin(request, basicImage);
        }

        private void saveTest1() {
            //스터디 이름 직접넣어서 좋재하지 않을시 수동으로 바꿔줘야함
            //test1
            UploadFileEntity basicImage = uploadFileRepository.findById(ImageConst.BASIC_MEMBER_IMAGE)
                    .orElseThrow(UploadFileNotFound::new);
            // 리더인 스터디 130
            MemberEntity member = memberService.getMember(1L);
            StudyEntity study = studyService.getStudyByLeader(1L);
            //리더가 아닌 가입 스터디
            final Long studyNotLeader = 50L;
            studyMemberService.createStudyMember(StudyMemberCreate.builder().memberSeq(member.getSequence()).studySeq(studyNotLeader).build());
            //팀장 아닐때 지원한 스터디
            final Long studyNotLeader2 = 49L;
            studyResumeService.createStudyResume(StudyResumeCreate.builder().memberSeq(member.getSequence()).studySeq(studyNotLeader2).content("지원할까말까").build());

            //지원안한 스터디
            final Long studyNotApply = 48L;

        }

        private void saveStudyMember() {
//            for (int i = 0; i < 10; i++) {
//                long memberSequence = 50 + (3 * i);
//                MemberEntity findmember = memberService.getMember(memberSequence);
//                StudyEntity findStudy = studyService.getStudyByLeader(findmember.getSequence());
//                studyMemberService.createStudyMember(StudyMemberCreate.builder().
//                        studySeq(findStudy.getSequence())
//                        .memberSeq(findmember.getSequence())
//                        .build());
//            }
        }

        private void saveStudyFrequency() throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            java.util.Date date = formatter.parse("18:00");
            for (int i = 1; i <= 10; i++) {
                long memberSequence = i;
                MemberEntity findmember = memberService.getMember(memberSequence);
                StudyEntity findStudy = studyService.getStudyByLeader(findmember.getSequence());

                studyFrequencyService.createStudyFrequency(findStudy.getSequence(),
                        StudyFrequencyCreate
                                .builder()
                                .studySeq(findStudy.getSequence())
                                .week("월")
                                .startTime(formatter.format(date))
                                .endTime(formatter.format(date))
                                .build());
            }
        }

        private void saveRank() {
            rankDummy.renewalStudyYesterdayRank();
            rankDummy.renewalStudyWeekRank();
            rankDummy.renewalStudyMonthRank();
            rankDummy.renewalPersonYesterdayRank();
            rankDummy.renewalPersonWeekRank();
            rankDummy.renewalPersonMonthRank();

        }

        private void saveTimer() {
            String[] category = {"etc", "project", "computer-science", "algorithm", "mobile", "infra", "frontend", "backend"};

            for (int i = 1; i <= 10; i++) {
                long memberSequence = i;
                for (int count = 1; count <= 30; count++) {
                    int categoryIdx = (int) (Math.random() * 7);
                    Date day = Date.valueOf(LocalDate.now().minusDays(count));
                    memberActivityService.saveMemberAcitivity(
                            new ActivityRequest((long) (Math.random() * 50), category[categoryIdx]), memberSequence,
                            day);
                }
                for (int count = 1; count <= 30; count++) {
                    int categoryIdx = (int) (Math.random() * 7);
                    Date day = Date.valueOf(LocalDate.now().minusDays(count));
                    memberActivityService.saveMemberAcitivity(
                            new ActivityRequest((long) (Math.random() * 50), category[categoryIdx]), memberSequence,
                            day);
                }

            }
            for (long i = 1; i < 51; i++) {
                for (int count = 1; count <= 30; count++) {
                    int categoryIdx = (int) (Math.random() * 7);
                    Date day = Date.valueOf(LocalDate.now().minusDays(count));
                    roomActivityService.saveRoomActivity(
                            new ActivityRequest((long) (Math.random() * 50), category[categoryIdx]), i,
                            day);
                }
                for (int count = 1; count <= 30; count++) {
                    int categoryIdx = (int) (Math.random() * 7);
                    Date day = Date.valueOf(LocalDate.now().minusDays(count));
                    roomActivityService.saveRoomActivity(
                            new ActivityRequest((long) (Math.random() * 50), category[categoryIdx]), i,
                            day);
                }

            }
        }

        private void saveCommunity() {
            CategoryEntity backend = categoryRepository.findByKey("backend")
                    .orElseThrow(() -> new InvalidRequest("category", "잘못된 카테고리 이름입니다."));
            for (int i = 1; i <= 10; i++) {
                long memberSequence = i;
                MemberEntity findMember = memberService.getMember(memberSequence);
                for (int count = 1; count <= 5; count++)
                    communityService.createCommunity(makeBoard(count), findMember, backend);
            }

            CategoryEntity frontend = categoryRepository.findByKey("frontend")
                    .orElseThrow(() -> new InvalidRequest("category", "잘못된 카테고리 이름입니다."));
            for (int i = 11; i <= 20; i++) {
                long memberSequence = i;
                MemberEntity findMember = memberService.getMember(memberSequence);
                for (int count = 1; count <= 5; count++)
                    communityService.createCommunity(makeBoard(count), findMember, frontend);
            }
        }

        private CommunityCreate makeBoard(int number) {
            return CommunityCreate.builder()
                    .title("제목" + number)
                    .content("내용" + number)
                    .isHighlighted(false)
                    .build();
        }

        private void saveMember() {
            UploadFileEntity basicImage = uploadFileRepository.findById(ImageConst.BASIC_MEMBER_IMAGE)
                    .orElseThrow(UploadFileNotFound::new);
            for (int i = 1; i <= 10; i++) {
                MemberCreate request = makeMemberCreate(i, new String[]{"Java", "Spring"});
                memberService.saveMember(request, basicImage);
            }
            for (int i = 11; i <= 20; i++) {
                MemberCreate request = makeMemberCreate(i, new String[]{"React", "Vue"});
                memberService.saveMember(request, basicImage);
            }
        }

        private void saveStudy() {
            UploadFileEntity basicImage = uploadFileRepository.findById(ImageConst.BASIC_MEMBER_IMAGE)
                    .orElseThrow(UploadFileNotFound::new);
            for (int i = 1; i <= 10; i++) {
                long memberSequence = (i);
                MemberEntity findmember = memberService.getMember(memberSequence);
                for (int count = 1; count <= 5; count++) {
                    if (count % 2 == 0) {
                        Long studyId = studyService.createStudy(StudyCreate.builder().name("스터디" + memberSequence)
                                .leaderSeq(findmember.getSequence()).introduction("아주 좋은 스터디1").population(32).level("초보")
                                .meeting("온라인").github("https://github.com").notion("https://notion.com")
                                .topCategory("backend").bottomCategory("java").leader(findmember.getNickname())
                                .build(), basicImage);
                        studyMemberService.createStudyMember(StudyMemberCreate.builder().memberSeq(findmember.getSequence()).studySeq(studyId).build());
                    } else {

                        Long studyId = studyService.createStudy(StudyCreate.builder().name("스터디" + memberSequence)
                                .leaderSeq(findmember.getSequence()).introduction("아주 좋은 스터디2").population(32).level("초보")
                                .meeting("온라인").github("https://github.com").notion("https://notion.com")
                                .topCategory("backend").bottomCategory("java").leader(findmember.getNickname())
                                .build(), basicImage);
                        studyMemberService.createStudyMember(StudyMemberCreate.builder().memberSeq(findmember.getSequence()).studySeq(studyId).build());
                    }
                }
            }
        }

        public void saveStudyRoom() {
            for (long i = 1; i <= 50; i++)
                studyRoomService.saveStudyRoom(i);
        }

        private void saveCategory() {
            CategoryEntity frontend = createMainCategory("frontend", "FRONT-END", 0L);
            categoryRepository.save(frontend);
            CategoryEntity backend = createMainCategory("backend", "BACK-END", 0L);
            categoryRepository.save(backend);
            CategoryEntity infra = createMainCategory("infra", "INFRA", 0L);
            categoryRepository.save(infra);
            CategoryEntity mobile = createMainCategory("mobile", "MOBILE", 0L);
            categoryRepository.save(mobile);
            CategoryEntity algorithm = createMainCategory("algorithm", "ALGORITHM", 0L);
            categoryRepository.save(algorithm);
            CategoryEntity computerScience = createMainCategory("computer-science", "CS", 0L);
            categoryRepository.save(computerScience);
            CategoryEntity project = createMainCategory("project", "PROJECT", 0L);
            categoryRepository.save(project);
            CategoryEntity etc = createMainCategory("etc", "ETC", 0L);
            categoryRepository.save(etc);

            makeFrontSubCategory(frontend);
            makeBackSubCategory(backend);
            makeInfraSubCategory(infra);
            makeMobileSubCategory(mobile);
            makeAlgorithmSubCategory(algorithm);
            makeComputerScienceSubCategory(computerScience);
            makeProjectSubCategory(project);
            makeEctSubCategory(etc);
        }

        private void makeEctSubCategory(CategoryEntity etc) {
            categoryRepository.save(createSubCategory("git", "Git", 1L, etc));
            categoryRepository.save(createSubCategoryWithImage("figma", "Figma", 1L, etc, 5L));
        }

        private void makeProjectSubCategory(CategoryEntity project) {
            categoryRepository.save(createSubCategory("project", "Project", 1L, project));
        }

        private void makeComputerScienceSubCategory(CategoryEntity computerScience) {
            categoryRepository.save(createSubCategory("dataStructure", "자료구조", 1L, computerScience));
            categoryRepository.save(createSubCategory("operatingSystem", "운영체제", 1L, computerScience));
            categoryRepository.save(createSubCategory("network", "네트워크", 1L, computerScience));
            categoryRepository.save(createSubCategory("computer-architecture", "컴퓨터구조", 1L, computerScience));
        }

        private void makeAlgorithmSubCategory(CategoryEntity algorithm) {
            categoryRepository.save(createSubCategory("bronze", "Bronze", 1L, algorithm));
            categoryRepository.save(createSubCategory("silver", "Silver", 1L, algorithm));
            categoryRepository.save(createSubCategory("gold", "Gold", 1L, algorithm));
            categoryRepository.save(createSubCategory("platinum", "Platinum", 1L, algorithm));
        }

        private void makeMobileSubCategory(CategoryEntity mobile) {
            categoryRepository.save(createSubCategory("flutter", "Flutter", 1L, mobile));
            categoryRepository.save(createSubCategory("swift", "Swift", 1L, mobile));
            categoryRepository.save(createSubCategory("kotlinMobile", "Kotlin", 1L, mobile));
            categoryRepository.save(createSubCategory("react-native", "ReactNative", 1L, mobile));
            categoryRepository.save(createSubCategory("unity", "Unity", 1L, mobile));
        }

        private void makeBackSubCategory(CategoryEntity backend) {
            categoryRepository.save(createSubCategory("java", "Java", 1L, backend));
            categoryRepository.save(createSubCategory("spring", "Spring", 1L, backend));
            categoryRepository.save(createSubCategory("nodejs", "NodeJs", 1L, backend));
            categoryRepository.save(createSubCategory("nestjs", "NestJs", 1L, backend));
            categoryRepository.save(createSubCategory("go", "Go", 1L, backend));
            categoryRepository.save(createSubCategory("kotlinBackend", "Kotlin", 1L, backend));
            categoryRepository.save(createSubCategory("express", "Express", 1L, backend));
            categoryRepository.save(createSubCategory("python", "Python", 1L, backend));
            categoryRepository.save(createSubCategory("django", "Django", 1L, backend));
            categoryRepository.save(createSubCategory("php", "php", 1L, backend));
            categoryRepository.save(createSubCategory("mysql", "MySql", 1L, backend));
            categoryRepository.save(createSubCategory("mongodb", "MongoDB", 1L, backend));
        }

        private void makeInfraSubCategory(CategoryEntity infra) {
            categoryRepository.save(createSubCategory("docker", "Docker", 1L, infra));
            categoryRepository.save(createSubCategory("kubernetes", "Kubernetes", 1L, infra));
            categoryRepository.save(createSubCategory("aws", "AWS", 1L, infra));
            categoryRepository.save(createSubCategory("jenkins", "Jenkins", 1L, infra));
        }

        private void makeFrontSubCategory(CategoryEntity frontend) {
            categoryRepository.save(createSubCategory("javascript", "JavaScript", 1L, frontend));
            categoryRepository.save(createSubCategory("typescript", "TypeScript", 1L, frontend));
            categoryRepository.save(createSubCategory("react", "React", 1L, frontend));
            categoryRepository.save(createSubCategoryWithImage("vue", "Vue", 1L, frontend, 4L));
            categoryRepository.save(createSubCategory("nextjs", "NextJs", 1L, frontend));
            categoryRepository.save(createSubCategory("svelte", "Svelte", 1L, frontend));
        }

        private void saveImageFile() {
            UploadFileEntity basicMemberImage = new UploadFileEntity("basic_member.png", "basic_member.png");
            uploadFileRepository.save(basicMemberImage);
            UploadFileEntity basicFrontEndImage = new UploadFileEntity("basic_frontend.jpg", "basic_frontend.jpg");
            uploadFileRepository.save(basicFrontEndImage);
            UploadFileEntity basicBackEndImage = new UploadFileEntity("basic_backend.png", "basic_backend.png");
            uploadFileRepository.save(basicBackEndImage);
            UploadFileEntity vuejsImage = new UploadFileEntity("vuejs.svg", "vuejs.svg");
            uploadFileRepository.save(vuejsImage);
            UploadFileEntity figmaImage = new UploadFileEntity("figma.svg", "figma.svg");
            uploadFileRepository.save(figmaImage);
        }


        private CategoryEntity createSubCategory(String key, String value, Long level, CategoryEntity parent) {
            CategoryEntity subCategory = CategoryEntity.builder()
                    .key(key)
                    .value(value)
                    .categoryLevel(level)
                    .build();
            subCategory.addParentCategory(parent);
            return subCategory;
        }

        private CategoryEntity createSubCategoryWithImage(String key, String value, Long level, CategoryEntity parent, Long imageSequence) {
            UploadFileEntity imageFile = uploadFileRepository.findById(imageSequence)
                    .orElseThrow(UploadFileNotFound::new);
            CategoryEntity subCategory = CategoryEntity.builder()
                    .key(key)
                    .value(value)
                    .categoryLevel(level)
                    .imageFile(imageFile)
                    .build();
            subCategory.addParentCategory(parent);
            return subCategory;
        }

        private CategoryEntity createMainCategory(String key, String value, Long level) {
            return CategoryEntity.builder()
                    .key(key)
                    .value(value)
                    .categoryLevel(level)
                    .build();
        }
    }
}
