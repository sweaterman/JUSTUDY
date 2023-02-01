package com.justudy.backend.community.service;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.service.CategoryService;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunityCreate;
import com.justudy.backend.community.dto.request.CommunityEdit;
import com.justudy.backend.community.dto.response.CommunityResponse;
import com.justudy.backend.community.exception.CommunityNotFound;
import com.justudy.backend.community.repository.CommunityLoveRepository;
import com.justudy.backend.community.repository.CommunityRepository;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.exception.ForbiddenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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
    private final CommunityLoveRepository communityLoveRepository;
    private final CategoryService categoryService;

    private final int MAX_PAGE_SIZE = 10;
    private final int MAX_NOTICE_SIZE = 3;

// ---------------------------------------------------------------커뮤니티---------------------------------------------------------------

    @Transactional
    public CommunityResponse createCommunity(CommunityCreate request, MemberEntity findMember, CategoryEntity category) {
        CommunityEntity community = request.toEntity();
        community.addMember(findMember);
        community.changeCategory(category);
        CommunityEntity savedCommunity = communityRepository.save(community);

        return CommunityResponse.makeBuilder(savedCommunity, 0);
    }

    @Transactional
    public Long deleteCommunity(Long loginSequence, Long communitySequence) {
        CommunityEntity community = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        validateWriter(loginSequence, community.getMember().getSequence());
        community.deleteCommunity();
        return community.getSequence();
    }

    @Transactional
    public CommunityResponse readCommunity(Long communitySequence) {
        CommunityEntity community = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        community.addViewCount();

        Integer loveCount = communityLoveRepository.readLoveCountByCommunity(communitySequence);

        return CommunityResponse.makeBuilder(community, loveCount);
    }

    @Transactional
    public CommunityResponse updateCommunity(Long loginSequence, Long communitySequence, CommunityEdit request) {
        CommunityEntity community = communityRepository.findById(communitySequence)
                .orElseThrow(CommunityNotFound::new);
        validateWriter(loginSequence, community.getMember().getSequence());

        community.update(request.getTitle(),
                request.getContent(),
                categoryService.getCategoryEntityByKey(request.getCategory()));

        Integer loveCount = communityLoveRepository.readLoveCountByCommunity(communitySequence);
        return CommunityResponse.makeBuilder(community, loveCount);
    }

    @Transactional
    public List<CommunityResponse> readAllCommunity(int page, String category) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        //맨 처음페이지는 공지3개 추가
        //공지 없을 시 일반글로 출력
        Long noticeCount = communityRepository.noticeCount();
        if (page == 0 && noticeCount > 0) {
            noticeCount = noticeCount < MAX_NOTICE_SIZE ? noticeCount : MAX_NOTICE_SIZE;
            Pageable noticePageable = PageRequest.of(0, noticeCount.intValue());
            Pageable categoryPageable = PageRequest.of(0, MAX_PAGE_SIZE - noticeCount.intValue());

            List<CommunityResponse> noticeList = communityRepository.findAllByNotice(noticePageable)
                    .stream()
                    .map(CommunityResponse::makeBuilder)
                    .collect(Collectors.toList());
            List<CommunityResponse> categoryList = communityRepository.findAll(categoryPageable, category)
                    .stream()
                    .map(CommunityResponse::makeBuilder)
                    .collect(Collectors.toList());

            noticeList.addAll(categoryList);
            return noticeList;
        }

        return communityRepository.findAll(pageable, category)
                .stream()
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<CommunityResponse> readAllNoticeCommunity(int page) {
        Pageable pageable = PageRequest.of(page, MAX_PAGE_SIZE);
        return communityRepository.findAllByNotice(pageable)
                .stream()
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<CommunityResponse> search(int page, String type, String search) {
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
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    public List<CommunityResponse> readPopularCommunity() {
        return communityRepository.findPopularCommunity()
                .stream()
                .map(CommunityResponse::makeBuilder)
                .collect(Collectors.toList());
    }

    private void validateWriter(Long loginSequence, Long writerSequence) {
        if (loginSequence != writerSequence) {
            throw new ForbiddenRequest();
        }
    }

}
