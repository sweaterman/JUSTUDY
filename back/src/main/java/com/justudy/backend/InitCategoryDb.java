package com.justudy.backend;

import com.justudy.backend.category.domain.CategoryEntity;
import com.justudy.backend.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class InitCategoryDb {

    private final InitCategoryService initCategoryService;

    @PostConstruct
    public void init() {
        initCategoryService.categoryInit();
    }

    @RequiredArgsConstructor
    @Component
    @Transactional
    static class InitCategoryService {

        private final CategoryRepository categoryRepository;

        public void categoryInit() {

            saveMainCategory();
        }

        private void saveMainCategory() {
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
