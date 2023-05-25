package com.it.academy.services.impl;

import com.it.academy.exceptions.AppException;
import com.it.academy.models.Course;
import com.it.academy.models.Review;
import com.it.academy.models.User;
import com.it.academy.repositories.ReviewRepository;
import com.it.academy.services.CourseService;
import com.it.academy.services.ReviewService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repo;
    private final UserService userService;
    private final CourseService courseService;

    @Override
    public Review getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new AppException("Review not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Review> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Long userId, Long courseId, Review review) {
        Course course = courseService.getById(courseId);
        User user = userService.getById(userId);

        review.setDate(LocalDate.now());
        review.setCourse(course);
        review.setUser(user);

        return repo.save(review).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Review updatedReview) {
        Review review = getById(id);

        review.setTitle(updatedReview.getTitle());
        review.setDescription(updatedReview.getDescription());
        review.setDate(updatedReview.getDate());

        return repo.save(review).getId();
    }

}
