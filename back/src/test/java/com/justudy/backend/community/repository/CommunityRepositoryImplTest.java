package com.justudy.backend.community.repository;

import com.justudy.backend.community.service.CommunityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CommunityRepositoryImplTest {

    @Autowired
    private CommunityRepository repository;

    private Pageable pageable;
    Logger log;
    @BeforeEach
    public void setUp() {
//        repository = new CommunityRepository(repository,loveRepository);
        log = (Logger) LoggerFactory.getLogger(CommunityRepository.class);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    void findAllByNotice() {
        repository.findAllByNotice(pageable);
    }

    @Test
    void findAllBySearchOption() {
    }

    @Test
    void findAll() {
    }
}