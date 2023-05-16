package com.it.academy.services.impl;

import com.it.academy.controllers.VideoController;
import com.it.academy.models.Lesson;
import com.it.academy.models.Video;
import com.it.academy.repositories.LessonRepository;
import com.it.academy.repositories.VideoRepository;
import com.it.academy.services.FileStorageService;
import com.it.academy.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "video")
public class VideoServiceImpl implements VideoService {
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    private final VideoRepository videoRepository;
    private final FileStorageService fileStorageService;
    private final LessonRepository lessonRepository;

    @Override
    public Video getById(Long id) {
        return videoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Video not found with id: " + id));
    }

    @Override
    public Video getByLessonId(Long lessonId) {
        return videoRepository.findByLessonId(lessonId).stream().findAny().orElseThrow(
                () -> new EntityNotFoundException("Lesson not found with id: " + lessonId));
    }

    @Override
    public Video uploadVideo(MultipartFile file, String title, Long lessonId) {
        String fileName = fileStorageService.storeFile(file);

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new EntityNotFoundException("Lesson not found with id: " + lessonId));

        Video video = Video.builder()
                .lesson(lesson)
                .title(title)
                .size(file.getSize())
                .url(fileName)
                .created(LocalDate.now())
                .build();
        videoRepository.save(video);

        return video;
    }

    @Override
    @Cacheable
    public ResponseEntity<Resource> downloadFile(Long id, HttpServletRequest request) {
        Video video = videoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Video not found with id: " + id));

        Resource resource = fileStorageService.loadFileAsResource(video.getUrl());

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if (contentType == null)
            contentType = "application/octet-stream";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
