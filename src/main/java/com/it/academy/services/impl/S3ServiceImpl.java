package com.it.academy.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import com.it.academy.dao.UserDao;
import com.it.academy.entities.S3;
import com.it.academy.repositories.S3Repository;
import com.it.academy.services.CourseService;
import com.it.academy.services.LessonService;
import com.it.academy.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {
    @Value("${aws-s3-bucketName}")
    private String bucketName;

    private final AmazonS3 amazonS3;
    private final S3Repository s3Repository;
    private final LessonService lessonService;
    private final CourseService courseService;
    private final UserDao userDao;

    @Override
    public String saveVideo(Long lessonId, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {
            S3 s3 = S3.builder()
                    .lesson(lessonService.getById(lessonId))
                    .createDate(LocalDate.now())
                    .url(file.getOriginalFilename())
                    .size(file.getSize())
                    .build();
            s3Repository.save(s3);

            File newFile = convertMultiPartToFile(file);
            amazonS3.putObject(bucketName, originalFilename, newFile);

            return file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String saveUserImage(Long userId, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {
            userDao.setImageUrl(originalFilename, userId);
            File newFile = convertMultiPartToFile(file);
            amazonS3.putObject(bucketName, originalFilename, newFile);

            return originalFilename;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String saveCourseImage(Long courseId, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {
            S3 s3 = S3.builder()
                    .course(courseService.getById(courseId))
                    .createDate(LocalDate.now())
                    .url(file.getOriginalFilename())
                    .size(file.getSize())
                    .build();
            s3Repository.save(s3);

            File newFile = convertMultiPartToFile(file);
            amazonS3.putObject(bucketName, originalFilename, newFile);

            return file.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Cacheable("file")
    @Override
    public byte[] downloadFile(String filename) {
        S3Object s3Object = amazonS3.getObject(bucketName, filename);
        S3ObjectInputStream objectContent = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(String filename) {
        amazonS3.deleteObject(bucketName, filename);
        return "File deleted";
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries()
                .stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("prefix-", "-suffix");
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
