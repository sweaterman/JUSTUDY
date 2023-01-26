package com.justudy.backend.file.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileEntity {

    @Id @GeneratedValue
    private Long sequence;
}
