package com.it.academy.services.impl;

import com.it.academy.models.Course;
import com.it.academy.models.Review;
import com.it.academy.models.User;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.repositories.ReviewRepository;
import com.it.academy.repositories.UserRepository;
import com.it.academy.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repo;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public Review getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Review not found with id: " + id));
    }

    @Override
    public List<Review> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Long userId, Long courseId, Review review) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new EntityNotFoundException("Course not found with id: " + courseId));
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + userId));

        review.setDate(LocalDate.now());
        review.setCourse(course);
        review.setUser(user);

        return repo.save(review).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Review updatedReview) {
        Review review = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Review not found with id: " + id));

        review.setTitle(updatedReview.getTitle());
        review.setDescription(updatedReview.getDescription());
        review.setDate(updatedReview.getDate());

        return repo.save(review).getId();
    }

}
