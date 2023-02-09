package com.justudy.backend.study.service.community;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.response.ListResult;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.exception.ForbiddenRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.study.domain.community.StudyCommunityEntity;
import com.justudy.backend.study.dto.request.community.StudyCommunityCreate;
import com.justudy.backend.study.dto.request.community.StudyCommunityEdit;
import com.justudy.backend.study.dto.response.community.StudyCommunityDetailResponse;
import com.justudy.backend.study.dto.response.community.StudyCommunityListResponse;
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
    private final CategoryService categoryService;
    private final StudyCommunityBookmarkService bookmarkService;
    private final StudyCommunityLoveService loveService;

    private final int MAX_PAGE_SIZE = 10;
    private final int MAX_NOTICE_SIZE = 3;

// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public StudyCommunityDetailResponse createStudyCommunity(StudyCommunityCreate request, MemberEntity findMember, CategoryEntity category) {
        StudyCommunityEntity community = request.toEntity();
        community.changeCategory(category);
        StudyCommunityEntity savedCommunity = communityRepository.save(community);

        return StudyCommunityDetailResponse.makeBuilder(savedCommunity, true, false, false);
    }

    @Transactional
    public Long deleteStudyCommunity(Long loginSequence, Long communitySequence) {
        StudyCommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        validateWriter(loginSequence, community.getMember().getSequence());
        community.deleteCommunity();
        bookmarkService.deleteBookmarkByCommunity(communitySequence);
        loveService.deleteAllByCommunity(communitySequence);
        return community.getSequence();
    }

    @Transactional
    public StudyCommunityDetailResponse readStudyCommunityDetail(Long communitySequence, Long loginSequence) {
        StudyCommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        boolean isWriter = community.getMember().getSequence() == loginSequence;
        boolean isBookmarked = bookmarkService.findBookmark(loginSequence, communitySequence).isPresent();
        boolean isLoved = loveService.findLove(loginSequence, communitySequence).isPresent();

        community.addViewCount();
        return StudyCommunityDetailResponse.makeBuilder(community,isWriter, isBookmarked, isLoved);
    }

    @Transactional
    public StudyCommunityDetailResponse updateCommunity(Long loginSequence, Long communitySequence, StudyCommunityEdit request) {
        StudyCommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        validateWriter(loginSequence, community.getMember().getSequence());

        community.update(request.getTitle(),
                request.getContent(),
                categoryService.getCategoryEntityByKey(request.getCategory()));

        return StudyCommunityDetailResponse.makeBuilder(community, true, false, false);
    }

    public ListResult<List<StudyCommunityListResponse>> getCommunities(CommunitySearch condition) {
        List<StudyCommunityListResponse> communityList = communityRepository.getAllList(condition).stream()
                .map(StudyCommunityListResponse::new).collect(Collectors.toList());
        Long countOfList = communityRepository.getCountOfList(condition);

        return new ListResult<>(communityList, countOfList);
    }

    public ListResult<List<StudyCommunityListResponse>> getNotices(Pageable pageable) {
        List<StudyCommunityListResponse> noticeList = communityRepository.getAllNotice(pageable).stream()
                .map(StudyCommunityListResponse::new).collect(Collectors.toList());
        Long countOfNotices = communityRepository.getCountOfNotices();

        return new ListResult<>(noticeList, countOfNotices);
    }

    public List<StudyCommunityListResponse> getMostLoveCommunitiesOfWeek(Pageable pageable) {
        return communityRepository.getMostLoveListOfWeek(pageable).stream()
                .map(StudyCommunityListResponse::new).collect(Collectors.toList());
    }

    public List<StudyCommunityListResponse> getMyBookmarks(Long loginSequence) {
        List<Long> sequences = bookmarkService.getMyBookmarks(loginSequence);
        log.info("[getMyBookmarks] sequences = {}", sequences);
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
        if (loginSequence != writerSequence) {
            throw new ForbiddenRequest();
        }
    }
}
