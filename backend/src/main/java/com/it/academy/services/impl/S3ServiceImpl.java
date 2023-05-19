package com.it.academy.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.it.academy.models.S3;
import com.it.academy.repositories.LessonRepository;
import com.it.academy.repositories.S3Repository;
import com.it.academy.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    @Value("${bucketName}")
    private String bucketName;

    private final AmazonS3 s3;
    private final S3Repository s3Repository;
    private final LessonRepository lessonRepository;

    @Override
    public String saveFile(Long lessonId, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        int count = 0;
        int maxTries = 3;
        while (true) {
            try {
                File newFile = convertMultiPartToFile(file);
                PutObjectResult putObjectResult = s3.putObject(bucketName, originalFilename, newFile);

                S3 s3 = S3.builder()
                        .lesson(lessonRepository.findById(lessonId).orElseThrow(
                                () -> new EntityNotFoundException("Lesson not found with id: " + lessonId)))
                        .createDate(LocalDate.now())
                        .url(file.getOriginalFilename())
                        .size(file.getSize())
                        .build();
                s3Repository.save(s3);

                return putObjectResult.getContentMd5();
            } catch (IOException e) {
                if (++count == maxTries) throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public byte[] downloadFile(String filename) {
        S3Object s3Object = s3.getObject(bucketName, filename);
        S3ObjectInputStream objectContent = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(String filename) {
        s3.deleteObject(bucketName, filename);
        return "File deleted";
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);
        return listObjectsV2Result.getObjectSummaries()
                .stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
