package com.justudy.backend.community.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.community.dto.response.CommunityDetailResponse;
import com.justudy.backend.community.dto.response.CommunityListResponse;
import com.justudy.backend.community.dto.response.ListResult;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.exception.ForbiddenRequest;
import com.justudy.backend.member.domain.MemberEntity;
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
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final CategoryService categoryService;
    private final CommunityBookmarkService bookmarkService;
    private final CommunityLoveService loveService;

    private final int MAX_PAGE_SIZE = 10;
    private final int MAX_NOTICE_SIZE = 3;

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
        bookmarkService.deleteBookmarkByCommunity(communitySequence);
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

    public ListResult<List<CommunityListResponse>> getCommunities(CommunitySearch condition) {
        List<CommunityListResponse> communityList = communityRepository.getAllList(condition).stream()
                .map(CommunityListResponse::new).collect(Collectors.toList());
        Long countOfList = communityRepository.getCountOfList(condition);

        return new ListResult<>(communityList, countOfList);
    }

    public ListResult<List<CommunityListResponse>> getNotices(Pageable pageable) {
        List<CommunityListResponse> noticeList = communityRepository.getAllNotice(pageable).stream()
                .map(CommunityListResponse::new).collect(Collectors.toList());
        Long countOfNotices = communityRepository.getCountOfNotices();

        return new ListResult<>(noticeList, countOfNotices);
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

    private void validateWriter(Long loginSequence, Long writerSequence) {
        if (loginSequence != writerSequence) {
            throw new ForbiddenRequest();
        }
    }


    @Transactional
    public List<CommunityDetailResponse> readAllCommunity(int page, String category) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        //맨 처음페이지는 공지3개 추가
        //공지 없을 시 일반글로 출력
        Long noticeCount = communityRepository.noticeCount();
        if (page == 0 && noticeCount > 0) {
            noticeCount = noticeCount < MAX_NOTICE_SIZE ? noticeCount : MAX_NOTICE_SIZE;
            Pageable noticePageable = PageRequest.of(0, noticeCount.intValue());
            Pageable categoryPageable = PageRequest.of(0, MAX_PAGE_SIZE - noticeCount.intValue());

            List<CommunityDetailResponse> noticeList = communityRepository.findAllByNotice(noticePageable)
                    .stream()
                    .map(CommunityDetailResponse::makeBuilder)
                    .collect(Collectors.toList());
            List<CommunityDetailResponse> categoryList = communityRepository.findAll(categoryPageable, category)
                    .stream()
                    .map(CommunityDetailResponse::makeBuilder)
                    .collect(Collectors.toList());

            noticeList.addAll(categoryList);
            return noticeList;
        }

        return communityRepository.findAll(pageable, category)
                .stream()
                .map(CommunityDetailResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<CommunityDetailResponse> readAllNoticeCommunity(int page) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        return communityRepository.findAllByNotice(pageable)
                .stream()
                .map(CommunityDetailResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<CommunityDetailResponse> search(int page, String type, String search) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        String name = null;
        String title = null;
        String content = null;

        if (type.compareTo("name") == 0) {
            name = search;
        } else if (type.compareTo("title") == 0) {
            title = search;
        } else {
            content = search;
        }

        return communityRepository.findAllBySearchOption(pageable, name, title, content)
                .stream()
                .map(CommunityDetailResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<CommunityDetailResponse> readPopularCommunity() {
        return communityRepository.findPopularCommunity()
                .stream()
                .map(CommunityDetailResponse::makeBuilder)
                .collect(Collectors.toList());
    }
}
