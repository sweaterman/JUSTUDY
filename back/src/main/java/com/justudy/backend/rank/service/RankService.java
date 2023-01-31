package com.justudy.backend.rank.service;

import com.justudy.backend.community.dto.response.CommunityResponse;
import com.justudy.backend.rank.common.RankGroup;
import com.justudy.backend.rank.common.RankType;
import com.justudy.backend.rank.dto.response.RankResponse;
import com.justudy.backend.rank.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankService {

    private final RankRepository rankRepository;

    @Transactional
    public List<RankResponse> readAllByGroupAndType(RankGroup rankGroup, RankType rankType){
        return rankRepository.findAllByGroupAndType(rankGroup, rankType);
    }
}
