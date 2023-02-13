package com.justudy.backend.file.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "upload_file")
@Entity
public class UploadFileEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_file_seq")
    private Long sequence;

    @Column(name = "upload_filename")
    private String uploadFileName; //고객이 업로드한 파일명

    @Column(name = "store_filename")
    private String storeFileName; //서버 내부에서 관리하는 파일명

    @Builder
    public UploadFileEntity(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
