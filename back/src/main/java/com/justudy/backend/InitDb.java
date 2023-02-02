package com.justudy.backend;

import com.justudy.backend.GroupCall.service.StudyRoomService;
import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.service.CommunityService;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.exception.UploadFileNotFound;
import com.justudy.backend.file.infra.ImageConst;
import com.justudy.backend.file.repository.UploadFileRepository;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.dto.request.MemberCreate;
import com.justudy.backend.exception.InvalidRequest;
import com.justudy.backend.member.service.MemberService;
import com.justudy.backend.study.dto.request.StudyCreate;
import com.justudy.backend.study.service.StudyService;
import com.justudy.backend.timer.dto.request.ActivityRequest;
import com.justudy.backend.timer.dto.request.ActivityRequest;
import com.justudy.backend.timer.service.MemberActivityService;
import com.justudy.backend.timer.service.RoomActivityService;
import java.sql.Date;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
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

        private final MemberActivityService memberActivityService;
        private final StudyRoomService studyRoomService;
        private final RoomActivityService roomActivityService;

        public void init() {
            saveCategory();
            saveImageFile();
            saveMember();
            saveCommunity();
            saveStudy();
            saveStudyRoom();
            saveTimer();
        }

        private void saveTimer() {
            for (int i = 0; i < 10; i++) {
                long memberSequence = 50 + (3 * i);
                for (int count = 1; count <= 30; count++) {
                    Date day = Date.valueOf(LocalDate.now().minusDays(count));
                    memberActivityService.saveMemberAcitivity(
                        new ActivityRequest((long) (Math.random() * 50), "frontend"), memberSequence,
                        day);
                }
                for (int count = 1; count <= 30; count++) {
                    Date day = Date.valueOf(LocalDate.now().minusDays(count));
                    memberActivityService.saveMemberAcitivity(
                        new ActivityRequest((long) (Math.random() * 50), "backend"), memberSequence,
                        day);
                }

            }
            for (long i = 180; i < 230; i++) {
                for (int count = 1; count <= 30; count++) {
                    Date day = Date.valueOf(LocalDate.now().minusDays(count));
                    roomActivityService.saveRoomAcitivity(
                        new ActivityRequest((long) (Math.random() * 50), "frontend"), i,
                        day);
                }
                for (int count = 1; count <= 30; count++) {
                    Date day = Date.valueOf(LocalDate.now().minusDays(count));
                    roomActivityService.saveRoomAcitivity(
                        new ActivityRequest((long) (Math.random() * 50), "backend"), i,
                        day);
                }

            }
        }
        private void saveCommunity() {
            CategoryEntity category = categoryRepository.findByKey("backend")
                    .orElseThrow(() -> new InvalidRequest("category", "잘못된 카테고리 이름입니다."));

            for (int i = 0; i < 10; i++) {
                long memberSequence = 50 + (3 * i);
                MemberEntity findMember = memberService.getMember(memberSequence);
                for (int count = 1; count <= 5; count++)
                    communityService.createCommunity(makeBoard(count), findMember, category);
            }
        }

        private CommunityCreate makeBoard(int number) {
            return CommunityCreate.builder()
                    .title("제목 " + number)
                    .content("내용 " + number)
                    .isHighlighted(false)
                    .build();
        }

        private void saveMember() {
            UploadFileEntity basicImage = uploadFileRepository.findById(ImageConst.BASIC_MEMBER_IMAGE)
                    .orElseThrow(UploadFileNotFound::new);

            for (int i = 1; i <= 10; i++) {
                MemberCreate request = makeMemberCreate(i);
                memberService.saveMember(request, basicImage);
            }
        }

        private void saveStudy(){
            UploadFileEntity basicImage = uploadFileRepository.findById(ImageConst.BASIC_MEMBER_IMAGE)
                    .orElseThrow(UploadFileNotFound::new);
            for(int i=0; i<10; i++){
                long memberSequence = 50 + (3*i);
                MemberEntity findmember = memberService.getMember(memberSequence);
                for(int count = 1; count <= 5; count++){
                    if(count%2 == 0) studyService.createStudy(StudyCreate.builder().name("스터디"+memberSequence)
                                    .leader("50L").introduction("아주 좋은 스터디1").population(32).level("초보")
                                    .meeting("온라인").github("https://github.com").notion("https://notion.com")
                            .build(),basicImage);
                    else studyService.createStudy(StudyCreate.builder().name("스터디"+memberSequence)
                            .leader("50L").introduction("아주 좋은 스터디2").population(32).level("초보")
                            .meeting("온라인").github("https://github.com").notion("https://notion.com")
                            .build(),basicImage);

                }
            }
        }
        public void saveStudyRoom(){
            for(long i =130;i<180;i++)
                studyRoomService.saveStudyRoom(i);
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
                    .category(new String[]{"Java", "Spring"})
                    .introduction("테스트 봇" + number + " 입니다.")
                    .build();
            return request;
        }

        private void saveImageFile() {
            UploadFileEntity basicMemberImage = new UploadFileEntity("basic_member.png", "basic_member.png");
            uploadFileRepository.save(basicMemberImage);
            UploadFileEntity basicFrontEndImage = new UploadFileEntity("basic_frontend.jpg", "basic_frontend.jpg");
            uploadFileRepository.save(basicFrontEndImage);
            UploadFileEntity basicBackEndImage = new UploadFileEntity("basic_backend.png", "basic_backend.png");
            uploadFileRepository.save(basicBackEndImage);
            UploadFileEntity basicAlgorithmImage = new UploadFileEntity("basic_algorithm.png", "basic_algorithm.png");
            uploadFileRepository.save(basicAlgorithmImage);
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
            makeEctSubCategory(etc);
        }

        private void makeEctSubCategory(CategoryEntity etc) {
            categoryRepository.save(createSubCategory("git", "Git", 1L, etc));
            categoryRepository.save(createSubCategory("figma", "Figma", 1L, etc));
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
            categoryRepository.save(createSubCategory( "kotlin", "Kotlin", 1L, mobile));
            categoryRepository.save(createSubCategory("react-native","ReactNative", 1L, mobile));
            categoryRepository.save(createSubCategory("unity", "Unity", 1L, mobile));
        }

        private void makeBackSubCategory(CategoryEntity backend) {
            categoryRepository.save(createSubCategory("java", "Java", 1L, backend));
            categoryRepository.save(createSubCategory("spring", "Spring", 1L, backend));
            categoryRepository.save(createSubCategory("nodejs", "NodeJs", 1L, backend));
            categoryRepository.save(createSubCategory("nestjs", "NestJs", 1L, backend));
            categoryRepository.save(createSubCategory("go", "Go", 1L, backend));
            categoryRepository.save(createSubCategory("kotlin", "Kotlin", 1L, backend));
            categoryRepository.save(createSubCategory("express", "Express", 1L, backend));
            categoryRepository.save(createSubCategory("python", "Python", 1L, backend));
            categoryRepository.save(createSubCategory("django", "Django", 1L, backend));
            categoryRepository.save(createSubCategory("php", "php", 1L, backend));
            categoryRepository.save(createSubCategory("mysql", "MySql", 1L, backend));
            categoryRepository.save(createSubCategory("mongodb","MongoDB", 1L, backend));
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
            categoryRepository.save(createSubCategory( "react", "React", 1L, frontend));
            categoryRepository.save(createSubCategory("vue", "Vue", 1L, frontend));
            categoryRepository.save(createSubCategory("nextjs","NextJs", 1L, frontend));
            categoryRepository.save(createSubCategory("svelte", "Svelte", 1L, frontend));
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

        private CategoryEntity createMainCategory(String key, String value, Long level) {
            return CategoryEntity.builder()
                    .key(key)
                    .value(value)
                    .categoryLevel(level)
                    .build();
        }
    }
}
