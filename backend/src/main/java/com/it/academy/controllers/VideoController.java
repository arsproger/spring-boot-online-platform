package com.it.academy.controllers;

import com.it.academy.dto.VideoDto;
import com.it.academy.mappers.VideoMapper;
import com.it.academy.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    private final VideoMapper videoMapper;

    @GetMapping
    public VideoDto getByLessonId(@RequestParam Long lessonId) {
        return videoMapper.map(videoService.getByLessonId(lessonId));
    }

    @PostMapping("/upload")
    public VideoDto uploadVideo(@RequestPart("file") MultipartFile file,
                                @RequestPart("title") String title,
                                @RequestPart("lessonId") Long lessonId) {
        return videoMapper.map(videoService.uploadVideo(file, title, lessonId));
    }

    @GetMapping("/get/{id:.+}")
    public VideoDto getVideo(@PathVariable Long id) {
        return videoMapper.map(videoService.getById(id));
    }

    @GetMapping("/download/{id:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, HttpServletRequest request) {
        return videoService.downloadFile(id, request);
    }

}