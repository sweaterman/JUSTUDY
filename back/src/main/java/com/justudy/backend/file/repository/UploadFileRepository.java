package com.justudy.backend.file.repository;

import com.justudy.backend.file.domain.UploadFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFileEntity, Long> {
}
