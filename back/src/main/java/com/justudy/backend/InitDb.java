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
import com.justudy.backend.member.exception.InvalidRequest;
import com.justudy.backend.member.service.MemberService;
import com.justudy.backend.study.domain.StudyFrequencyEntity;
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
import java.util.List;

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
            CategoryEntity category = categoryRepository.findByName("backend")
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
                                    .leaderSeq(50L).introduction("아주 좋은 스터디1").personnel(32).level("초보")
                                    .onlineOffline("온라인").isOpen(true).github("https://github.com").notion("https://notion.com")
                            .build(),basicImage);
                    else studyService.createStudy(StudyCreate.builder().name("스터디"+memberSequence)
                            .leaderSeq(50L).introduction("아주 좋은 스터디2").personnel(32).level("초보")
                            .onlineOffline("온라인").isOpen(false).github("https://github.com").notion("https://notion.com")
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
            CategoryEntity frontend = createMainCategory("frontend", 0L);
            categoryRepository.save(frontend);
            CategoryEntity backend = createMainCategory("backend", 0L);
            categoryRepository.save(backend);
            CategoryEntity mobile = createMainCategory("mobile", 0L);
            categoryRepository.save(mobile);
            CategoryEntity algorithm = createMainCategory("algorithm", 0L);
            categoryRepository.save(algorithm);
            CategoryEntity computerScience = createMainCategory("computer-science", 0L);
            categoryRepository.save(computerScience);
            CategoryEntity project = createMainCategory("project", 0L);
            categoryRepository.save(project);
            CategoryEntity etc = createMainCategory("etc", 0L);
            categoryRepository.save(etc);

            makeFrontSubCategory(frontend);
            makeBackSubCategory(backend);
            makeMobileSubCategory(mobile);
            makeAlgorithmSubCategory(algorithm);
            makeComputerScienceSubCategory(computerScience);
            makeEctSubCategory(etc);
        }

        private void makeEctSubCategory(CategoryEntity etc) {
            categoryRepository.save(createSubCategory("Docker", 1L, etc));
            categoryRepository.save(createSubCategory("Kubernetes", 1L, etc));
            categoryRepository.save(createSubCategory("AWS", 1L, etc));
            categoryRepository.save(createSubCategory("Git", 1L, etc));
            categoryRepository.save(createSubCategory("Jenkins", 1L, etc));
            categoryRepository.save(createSubCategory("Figma", 1L, etc));
        }

        private void makeComputerScienceSubCategory(CategoryEntity computerScience) {
            categoryRepository.save(createSubCategory("DataStructure", 1L, computerScience));
            categoryRepository.save(createSubCategory("OperatingSystem", 1L, computerScience));
            categoryRepository.save(createSubCategory("Network", 1L, computerScience));
            categoryRepository.save(createSubCategory("ComputerArchitecture", 1L, computerScience));
        }

        private void makeAlgorithmSubCategory(CategoryEntity algorithm) {
            categoryRepository.save(createSubCategory("Bronze", 1L, algorithm));
            categoryRepository.save(createSubCategory("Silver", 1L, algorithm));
            categoryRepository.save(createSubCategory("Gold", 1L, algorithm));
            categoryRepository.save(createSubCategory("Platinum", 1L, algorithm));
            categoryRepository.save(createSubCategory("Diamond", 1L, algorithm));
        }

        private void makeMobileSubCategory(CategoryEntity mobile) {
            categoryRepository.save(createSubCategory("Flutter", 1L, mobile));
            categoryRepository.save(createSubCategory("Swift", 1L, mobile));
            categoryRepository.save(createSubCategory("Kotlin", 1L, mobile));
            categoryRepository.save(createSubCategory("ReactNative", 1L, mobile));
            categoryRepository.save(createSubCategory("Unity", 1L, mobile));
        }

        private void makeBackSubCategory(CategoryEntity backend) {
            categoryRepository.save(createSubCategory("Java", 1L, backend));
            categoryRepository.save(createSubCategory("Spring", 1L, backend));
            categoryRepository.save(createSubCategory("NodeJs", 1L, backend));
            categoryRepository.save(createSubCategory("NestJs", 1L, backend));
            categoryRepository.save(createSubCategory("Go", 1L, backend));
            categoryRepository.save(createSubCategory("Kotlin", 1L, backend));
            categoryRepository.save(createSubCategory("Express", 1L, backend));
            categoryRepository.save(createSubCategory("Python", 1L, backend));
            categoryRepository.save(createSubCategory("Django", 1L, backend));
            categoryRepository.save(createSubCategory("php", 1L, backend));
            categoryRepository.save(createSubCategory("MySql", 1L, backend));
            categoryRepository.save(createSubCategory("MongoDB", 1L, backend));
        }

        private void makeFrontSubCategory(CategoryEntity frontend) {
            categoryRepository.save(createSubCategory("JavaScript", 1L, frontend));
            categoryRepository.save(createSubCategory("TypeScript", 1L, frontend));
            categoryRepository.save(createSubCategory("React", 1L, frontend));
            categoryRepository.save(createSubCategory("Vue", 1L, frontend));
            categoryRepository.save(createSubCategory("NextJs", 1L, frontend));
            categoryRepository.save(createSubCategory("Svelte", 1L, frontend));
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
}
