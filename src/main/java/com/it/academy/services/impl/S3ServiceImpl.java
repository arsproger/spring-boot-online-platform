package com.it.academy.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import com.it.academy.dao.CourseDao;
import com.it.academy.dao.LessonDao;
import com.it.academy.dao.UserDao;
import com.it.academy.services.CourseService;
import com.it.academy.services.LessonService;
import com.it.academy.services.S3Service;
import com.it.academy.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "file")
public class S3ServiceImpl implements S3Service {
    @Value("${aws-s3-bucketName}")
    private String bucketName;

    private final AmazonS3 amazonS3;
    private final LessonService lessonService;
    private final CourseService courseService;
    private final UserDao userDao;
    private final CourseDao courseDao;
    private final LessonDao lessonDao;
    private final UserService userService;

    @Override
    public String saveLessonVideo(Long lessonId, MultipartFile file) {
        lessonService.getById(lessonId);
        lessonDao.setVideoUrl(file.getOriginalFilename(), lessonId);
        return pushFile(file);
    }

    @Override
    public String saveUserImage(Long userId, MultipartFile file) {
        userService.getById(userId);
        userDao.setImageUrl(file.getOriginalFilename(), userId);
        return pushFile(file);
    }

    @Override
    public String saveCourseImage(Long courseId, MultipartFile file) {
        courseService.getById(courseId);
        courseDao.setImageUrl(file.getOriginalFilename(), courseId);
        return pushFile(file);
    }

    @Override
    @CachePut(key = "#file.originalFilename")
    public String pushFile(MultipartFile file) {
        try {
            amazonS3.putObject(bucketName, file.getOriginalFilename(), convertMultiPartToFile(file));
            return file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Cacheable(key = "#fileName")
    @Override
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream objectContent = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries()
                .stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(key = "#fileName")
    public String deleteFile(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
        return "File deleted";
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("prefix-", "-suffix");
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
