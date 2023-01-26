package com.justudy.backend.file.service;

import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.exception.UploadFileNotFound;
import com.justudy.backend.file.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UploadFileService {

    private final UploadFileRepository uploadFileRepository;

    @Transactional
    public Long saveUploadFile(UploadFileEntity uploadFile) {
        uploadFileRepository.save(uploadFile);
        return uploadFile.getSequence();
    }

    public UploadFileEntity getUploadFile(Long uploadFileSequence) {
        return uploadFileRepository.findById(uploadFileSequence)
                .orElseThrow(() -> new UploadFileNotFound());
    }

    @Transactional
    public Long deleteUploadFile(Long uploadFileSequence) {
        UploadFileEntity targetFile = uploadFileRepository.findById(uploadFileSequence)
                .orElseThrow(() -> new UploadFileNotFound());

        uploadFileRepository.delete(targetFile);
        return targetFile.getSequence();
    }
}
