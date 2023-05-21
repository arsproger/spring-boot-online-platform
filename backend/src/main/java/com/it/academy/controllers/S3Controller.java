package com.it.academy.controllers;

import com.it.academy.services.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с сервисом S3",
        description = "Сервис используется для загрузки и получения видео из облачного хранилища aws")
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping("/upload")
    @Operation(summary = "Загрузка видео на сервер")
    public String upload(@RequestParam Long lessonId, @RequestParam("file") MultipartFile file) {
        return s3Service.saveFile(lessonId, file);
    }

    @GetMapping("/download/{filename:.+}")
    @Operation(summary = "Получение видео из сервера по его имени")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "video/mp4");
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        byte[] bytes = s3Service.downloadFile(filename);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);
    }

    @DeleteMapping("/{filename}")
    @Operation(summary = "Удаление видео из сервера по его имени")
    public String deleteFile(@PathVariable("filename") String filename) {
        return s3Service.deleteFile(filename);
    }

    @GetMapping("/list")
    @Operation(summary = "Получение всех загруженных файлов с сервера")
    public List<String> getAllFiles() {
        return s3Service.listAllFiles();
    }

}
