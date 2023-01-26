package com.justudy.backend;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

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



        public void init() {
            saveCategory();
            saveImageFile();
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
