package com.it.academy.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Service {
    String saveVideo(Long lessonId, MultipartFile file);

    String saveCourseImage(Long courseId, MultipartFile file);

    String saveUserImage(Long userId, MultipartFile file);

    byte[] downloadFile(String filename);

    String deleteFile(String filename);

    List<String> listAllFiles();

}
