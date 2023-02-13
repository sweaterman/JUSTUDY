package com.justudy.backend.study.service.community;

import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.response.CommunityListResult;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.exception.ForbiddenRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.StudyEntity;
import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import com.justudy.backend.study.dto.request.community.StudyCommunityCreate;
import com.justudy.backend.study.dto.request.community.StudyCommunityEdit;
import com.justudy.backend.study.dto.response.community.StudyCommunityDetailResponse;
import com.justudy.backend.study.dto.response.community.StudyCommunityListResponse;
import com.justudy.backend.study.exception.StudyNotFound;
import com.justudy.backend.study.repository.StudyRepository;
import com.justudy.backend.study.repository.community.StudyCommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyCommunityService {

    private final StudyCommunityRepository communityRepository;
    private final StudyRepository studyRepository;
    private final StudyCommunityBookmarkService bookmarkService;
    private final StudyCommunityLoveService loveService;

// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public StudyCommunityDetailResponse createStudyCommunity(StudyCommunityCreate request, MemberEntity findMember, Long studySequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, findMember.getSequence());

        StudyCommunityEntity community = request.toEntity();
        community.addMember(findMember);
        community.addStudy(studyEntity);

        StudyCommunityEntity savedCommunity = communityRepository.save(community);
        return StudyCommunityDetailResponse.makeBuilder(savedCommunity, true, false, false);
    }


    @Transactional
    public void deleteStudyCommunity(Long loginSequence, Long communitySequence, Long studySequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, loginSequence);

        StudyCommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        validateWriter(loginSequence, community.getMember().getSequence());
        community.deleteCommunity();
        bookmarkService.deleteBookmarkByCommunity(communitySequence);
        loveService.deleteAllByCommunity(communitySequence);
    }

    @Transactional
    public StudyCommunityDetailResponse readStudyCommunityDetail(Long communitySequence, Long loginSequence, Long studySequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, loginSequence);

        StudyCommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        boolean isWriter = community.getMember().getSequence().longValue() == loginSequence.longValue();
        boolean isBookmarked = bookmarkService.findBookmark(loginSequence, communitySequence).isPresent();
        boolean isLoved = loveService.findLove(loginSequence, communitySequence).isPresent();

        community.addViewCount();
        return StudyCommunityDetailResponse.makeBuilder(community, isWriter, isBookmarked, isLoved);
    }

    @Transactional
    public StudyCommunityDetailResponse updateCommunity(Long loginSequence, Long communitySequence, StudyCommunityEdit request, Long studySequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, loginSequence);

        StudyCommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        validateWriter(loginSequence, community.getMember().getSequence());
        community.update(request.getTitle(),
                request.getContent(),
                studyEntity);

        return StudyCommunityDetailResponse.makeBuilder(community, true, false, false);
    }

    public CommunityListResult<List<StudyCommunityListResponse>> getCommunities(CommunitySearch condition, Long studySequence, Long loginSequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, loginSequence);

        List<StudyCommunityListResponse> communityList = communityRepository.getAllList(condition, studySequence).stream()
                .map(StudyCommunityListResponse::new).collect(Collectors.toList());
        Long countOfList = communityRepository.getCountOfList(condition, studySequence);

        return new CommunityListResult<>(communityList, countOfList);
    }

    public CommunityListResult<List<StudyCommunityListResponse>> getNotices(Pageable pageable, Long studySequence, Long loginSequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, loginSequence);

        List<StudyCommunityListResponse> noticeList = communityRepository.getAllNotice(pageable).stream()
                .map(StudyCommunityListResponse::new).collect(Collectors.toList());
        Long countOfNotices = communityRepository.getCountOfNotices();

        return new CommunityListResult<>(noticeList, countOfNotices);
    }

    public List<StudyCommunityListResponse> getMostLoveCommunitiesOfWeek(Pageable pageable, Long studySequence, Long loginSequence) {
        //스터디원인지 체크
        StudyEntity studyEntity = studyRepository.findById(studySequence).orElseThrow(StudyNotFound::new);
        isStudyMember(studyEntity, loginSequence);

        return communityRepository.getMostLoveListOfWeek(pageable).stream()
                .map(StudyCommunityListResponse::new).collect(Collectors.toList());
    }

    public List<StudyCommunityListResponse> getMyBookmarks(Long loginSequence) {
        List<Long> sequences = bookmarkService.getMyBookmarks(loginSequence);
        return communityRepository.getListBySequences(sequences).stream()
                .map(StudyCommunityListResponse::new)
                .collect(Collectors.toList());
    }

    public List<StudyCommunityListResponse> getMyLoves(Long loginSequence) {
        List<Long> sequences = loveService.getMyLoves(loginSequence);
        return communityRepository.getListBySequences(sequences).stream()
                .map(StudyCommunityListResponse::new)
                .collect(Collectors.toList());
    }

    private void validateWriter(Long loginSequence, Long writerSequence) {
        if (loginSequence.longValue() != writerSequence.longValue()) {
            throw new ForbiddenRequest();
        }
    }

    private void isStudyMember(StudyEntity studyEntity, Long sequence) {
        studyEntity.getStudyMembers()
                .stream()
                .map(studyMemberEntity -> studyMemberEntity.getMember().getSequence())
                .filter(memberSequence -> memberSequence.longValue() == sequence.longValue())
                .findFirst()
                .orElseThrow(ForbiddenRequest::new);
    }
}
