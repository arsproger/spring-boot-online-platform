package com.it.academy.repositories;

import com.it.academy.models.Video;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VideoRepository extends PagingAndSortingRepository<Video, Long> {
    List<Video> findByLessonId(Long id);
}
