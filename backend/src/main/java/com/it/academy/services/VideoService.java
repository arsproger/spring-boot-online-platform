package com.it.academy.services;

import com.it.academy.models.Video;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface VideoService {

    Video getById(Long id);

    Video getByLessonId(Long lessonId);

    Video uploadVideo(MultipartFile file, String title, Long lessonId);

    ResponseEntity<Resource> downloadFile(Long id, HttpServletRequest request);
}
