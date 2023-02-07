package com.justudy.backend.community.repository;

//import com.justudy.backend.GroupCall.config.WebSocketConfig;
import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.community.domain.CommunityEntity;
import com.justudy.backend.community.dto.request.CommunitySearch;
import com.justudy.backend.member.domain.MemberEntity;
import com.justudy.backend.member.repository.MemberRepository;
import com.justudy.backend.rank.repository.RankRepository;
import com.justudy.backend.study.repository.StudyFrequencyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest(excludeAutoConfiguration = {WebSocketConfig.class, RankRepository.class, StudyFrequencyRepository.class})
//@SpringBootTest
class CommunityRepositoryTest {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void getAllList() {
        //given
        CategoryEntity backend = CategoryEntity.builder().key("backend").value("백엔드").categoryLevel(0L).build();
        categoryRepository.save(backend);
        CategoryEntity frontend = CategoryEntity.builder().key("frontend").value("프론트엔드").categoryLevel(0L).build();
        categoryRepository.save(frontend);
        MemberEntity member = MemberEntity.builder().nickname("테스트 봇").build();
        memberRepository.save(member);

        for (int i = 1; i <= 30; i++) {
            CommunityEntity board = CommunityEntity.builder().title("백_제목" + i).content("내용" + i).isHighlighted(false).build();
            board.addMember(member);
            board.changeCategory(backend);
            communityRepository.save(board);
        }

        for (int i = 1; i <= 30; i++) {
            CommunityEntity board = CommunityEntity.builder().title("프_제목" + i).content("내용" + i).isHighlighted(false).build();
            board.addMember(member);
            board.changeCategory(frontend);
            communityRepository.save(board);
        }
        CommunitySearch condition = new CommunitySearch(0L, 20L, "backend", null, null, null);

        //when
        List<CommunityEntity> list = communityRepository.getAllList(condition);

        //then
        assertThat(list.size()).isEqualTo(20);
    }
}