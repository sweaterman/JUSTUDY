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
            saveMember();
            saveCommunity();
            saveStudy();
//            saveStudyFrequency();
//            saveStudyMember();
//            saveStudyRoom();
//            saveTimer();
//            saveRank();
//            saveTest1();
        }

        private void saveTest1() {
            //스터디 이름 직접넣어서 좋재하지 않을시 수동으로 바꿔줘야함
            //test1
            UploadFileEntity basicImage = uploadFileRepository.findById(ImageConst.BASIC_MEMBER_IMAGE)
                    .orElseThrow(UploadFileNotFound::new);
            // 리더인 스터디 130
            MemberEntity member = memberService.getMember(50L);
            StudyEntity study = studyService.getStudyByLeader(50L);
            //리더가 아닌 가입 스터디
            final Long studyNotLeader = 228L;
            studyMemberService.createStudyMember(StudyMemberCreate.builder().memberSeq(member.getSequence()).studySeq(studyNotLeader).build());
            //팀장 아닐때 지원한 스터디
            final Long studyNotLeader2 = 226L;
            studyResumeService.createStudyResume(StudyResumeCreate.builder().memberSeq(member.getSequence()).studySeq(studyNotLeader2).content("지원할까말까").build());

            //지원안한 스터디
            final Long studyNotApply = 177L;

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
            for (int i = 0; i < 10; i++) {
                long memberSequence = 50 + (3 * i);
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
            for (long i = 240; i < 290; i++) {
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
            for (int i = 0; i < 10; i++) {
                long memberSequence = 50 + (3 * i);
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
            for (long i = 130; i < 230; i += 2)
                studyRoomService.saveStudyRoom(i);
        }
    }
}
