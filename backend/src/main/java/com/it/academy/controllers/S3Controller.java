package com.it.academy.controllers;

import com.it.academy.services.FileService;
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
public class S3Controller {
    private final FileService s3Service;

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        return s3Service.saveFile(file);
    }

    @GetMapping("download/{filename:.+}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "video/mp4");
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        byte[] bytes = s3Service.downloadFile(filename);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);
    }

    @DeleteMapping("{filename}")
    public String deleteFile(@PathVariable("filename") String filename) {
        return s3Service.deleteFile(filename);
    }

    @GetMapping("list")
    public List<String> getAllFiles() {
        return s3Service.listAllFiles();
    }

}
