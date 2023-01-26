package com.justudy.backend.file.controller;


import com.justudy.backend.file.domain.UploadFileEntity;
import com.justudy.backend.file.service.FileStore;
import com.justudy.backend.file.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UploadFileController {

    private final UploadFileService uploadFileService;

    private final FileStore fileStore;

    @GetMapping("/images/{uploadFileSequence}") //화면에서 이미지보기
    public Resource showImage(@PathVariable Long uploadFileSequence) throws MalformedURLException {
        UploadFileEntity findFile = uploadFileService.getUploadFile(uploadFileSequence);
        String storeFileName = findFile.getStoreFileName();

        return new UrlResource("file:" + fileStore.getFullPath(storeFileName));
    }
}
