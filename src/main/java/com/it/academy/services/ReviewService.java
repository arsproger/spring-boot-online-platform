package com.it.academy.services;

import com.it.academy.entities.Review;

import java.util.List;

public interface ReviewService {
    Review getById(Long id);

    List<Review> getAll();

    Long save(Long userId, Long courseId, Review review);

    Long deleteById(Long id);

    Long update(Long id, Review review);
}
