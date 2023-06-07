package com.it.academy.services.impl;

import com.it.academy.dao.ReviewDao;
import com.it.academy.entities.Course;
import com.it.academy.entities.Review;
import com.it.academy.entities.User;
import com.it.academy.enums.Role;
import com.it.academy.exceptions.AppException;
import com.it.academy.repositories.ReviewRepository;
import com.it.academy.services.CourseService;
import com.it.academy.services.ReviewService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final CourseService courseService;
    private final ReviewDao reviewDao;

    @Override
    public Review getById(Long id) {
        return reviewRepository.findById(id).orElseThrow(
                () -> new AppException("Review not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByCourse(Long courseId) {
        return reviewDao.getReviewsByCourseId(courseId);
    }

    @Override
    public Double getCourseAvgGrade(Long courseId) {
        return reviewDao.getCourseAvgGrade(courseId);
    }

    @Override
    public Long save(Long userId, Long courseId, Review review) {
        Course course = courseService.getById(courseId);
        User user = userService.getById(userId);

        review.setDate(LocalDate.now());
        review.setCourse(course);
        review.setUser(user);

        return reviewRepository.save(review).getId();
    }

    @Override
    public List<Review> getCourseReviewsByAuthorId(Long authorId) {
        return reviewDao.getCourseReviewsByAuthorId(authorId);
    }

    @Override
    public Long deleteById(Long userId, Long id) {
        Review review = getById(id);
        User user = userService.getById(userId);

        if (!(userId.equals(review.getUser().getId()) || user.getRole().equals(Role.ROLE_ADMIN))) {
            throw new AccessDeniedException("You can't delete this review!");
        }

        reviewRepository.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long userId, Long id, Review updatedReview) {
        Review review = getById(id);

        if (!userId.equals(review.getUser().getId())) {
            throw new AccessDeniedException("You can't update this review!");
        }

        review.setTitle(updatedReview.getTitle());
        review.setDescription(updatedReview.getDescription());
        review.setDate(LocalDate.now());

        return reviewRepository.save(review).getId();
    }

    @Override
    public Integer getCountOfAllReviews() {
        return reviewDao.getCountOfAllReviews();
    }

}
