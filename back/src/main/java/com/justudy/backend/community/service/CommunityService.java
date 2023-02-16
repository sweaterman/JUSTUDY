package com.justudy.backend.community.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.response.CommunityDetailResponse;
import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.community.dto.response.CommunityListResult;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.exception.ForbiddenRequest;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.report.dto.request.CommunityReportReason;
import com.justudy.backend.report.dto.response.CommunityReportDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final CategoryService categoryService;
    private final CommunityBookmarkService bookmarkService;
    private final CommunityLoveService loveService;


// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public CommunityDetailResponse createCommunity(CommunityCreate request, MemberEntity findMember, CategoryEntity category) {
        CommunityEntity community = request.toEntity();
        community.addMember(findMember);
        community.changeCategory(category);
        CommunityEntity savedCommunity = communityRepository.save(community);

        return CommunityDetailResponse.makeBuilder(savedCommunity, true, false, false);
    }

    @Transactional
    public Long deleteCommunity(Long loginSequence, Long communitySequence) {
        CommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        validateWriter(loginSequence, community.getMember().getSequence());
        community.deleteCommunity();
        bookmarkService.deleteAllByCommunity(communitySequence);
        loveService.deleteAllByCommunity(communitySequence);
        return community.getSequence();
    }

    @Transactional
    public CommunityDetailResponse readCommunityDetail(Long communitySequence, Long loginSequence) {
        CommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        boolean isWriter = community.getMember().getSequence() == loginSequence;
        boolean isBookmarked = bookmarkService.findBookmark(loginSequence, communitySequence).isPresent();
        boolean isLoved = loveService.findLove(loginSequence, communitySequence).isPresent();

        community.addViewCount();
        return CommunityDetailResponse.makeBuilder(community,isWriter, isBookmarked, isLoved);
    }

    @Transactional
    public CommunityDetailResponse updateCommunity(Long loginSequence, Long communitySequence, CommunityEdit request) {
        CommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        validateWriter(loginSequence, community.getMember().getSequence());

        community.update(request.getTitle(),
                request.getContent(),
                categoryService.getCategoryEntityByKey(request.getCategory()));

        return CommunityDetailResponse.makeBuilder(community, true, false, false);
    }

    public CommunityReportDetail getReportDetail(Long communitySequence) {
        CommunityEntity community = communityRepository.findBySequence(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        Map<String, String> reasonMap = Arrays.stream(CommunityReportReason.class.getEnumConstants())
                .collect(Collectors.toMap(reason -> reason.getValue(), reason -> reason.getMessage()));

        return new CommunityReportDetail(community.getMember().getNickname(), community.getTitle(), reasonMap);
    }

    public CommunityListResult<List<CommunityListResponse>> getCommunities(CommunitySearch condition) {
        List<CommunityListResponse> communityList = communityRepository.getAllList(condition).stream()
                .map(CommunityListResponse::new).collect(Collectors.toList());
        Long countOfList = communityRepository.getCountOfList(condition);

        return new CommunityListResult<>(communityList, countOfList);
    }

    public CommunityListResult<List<CommunityListResponse>> getNotices(Pageable pageable) {
        List<CommunityListResponse> noticeList = communityRepository.getAllNotice(pageable).stream()
                .map(CommunityListResponse::new).collect(Collectors.toList());
        Long countOfNotices = communityRepository.getCountOfNotices();

        return new CommunityListResult<>(noticeList, countOfNotices);
    }

    public List<CommunityListResponse> getMostLoveCommunitiesOfWeek(Pageable pageable) {
        return communityRepository.getMostLoveListOfWeek(pageable).stream()
                .map(CommunityListResponse::new).collect(Collectors.toList());
    }

    public List<CommunityListResponse> getMyBookmarks(Long loginSequence) {
        List<Long> sequences = bookmarkService.getMyBookmarks(loginSequence);
        log.info("[getMyBookmarks] sequences = {}", sequences);
        return communityRepository.getListBySequences(sequences).stream()
                .map(CommunityListResponse::new)
                .collect(Collectors.toList());
    }

    public List<CommunityListResponse> getMyLoves(Long loginSequence) {
        List<Long> sequences = loveService.getMyLoves(loginSequence);
        return communityRepository.getListBySequences(sequences).stream()
                .map(CommunityListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Scheduled(zone = "Asia/Seoul", cron = "0 0 4 * * MON")
    void updateWeekLoveCount() {
        communityRepository.updateWeekLoveCount();
    }

    private void validateWriter(Long loginSequence, Long writerSequence) {
        if (loginSequence != writerSequence) {
            throw new ForbiddenRequest();
        }
    }
}
