package com.it.academy.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Service {
    String saveFile(Long lessonId, MultipartFile file);

    byte[] downloadFile(String filename);

    String deleteFile(String filename);

    List<String> listAllFiles();

}
