package com.it.academy.services;

import com.it.academy.entities.Review;

import java.util.List;

public interface ReviewService {
    Review getById(Long id);

    List<Review> getAll();

    List<Review> getReviewsByCourse(Long courseId);

    Long save(Long userId, Long courseId, Review review);

    Double getCourseAvgGrade(Long courseId);

    List<Review> getCourseReviewsByAuthorId(Long authorId);

    Long deleteById(Long userId, Long id);

    Long update(Long userId, Long id, Review updatedReview);
}
