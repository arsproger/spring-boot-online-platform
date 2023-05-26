package com.it.academy.services.impl;

import com.it.academy.enums.Role;
import com.it.academy.models.Cart;
import com.it.academy.models.Course;
import com.it.academy.models.User;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.services.CartService;
import com.it.academy.services.CategoryService;
import com.it.academy.services.CourseService;
import com.it.academy.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CartService cartService;

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course not found with id: " + id));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Long save(Long authorId, Long categoryId, Course course) {
        course.setAuthor(userService.getById(authorId));
        course.setCategory(categoryService.getById(categoryId));
        return courseRepository.save(course).getId();
    }

    @Override
    public Long create(Long authorId, Long categoryId, Course course) {
        User author = userService.getById(authorId);
        author.setRole(Role.ROLE_TRAINER);
        userService.save(author);

        Course createdCourse = Course.builder()
                .author(author)
                .category(categoryService.getById(categoryId))
                .name(course.getName())
                .description(course.getDescription())
                .language(course.getLanguage())
                .sections(course.getSections())
                .reviews(course.getReviews())
                .price(course.getPrice())
                .subscriptions(course.getSubscriptions())
                .build();

        if (author.getStripeAccountId() == null || author.getStripeAccountId().isEmpty()) return null;

        return courseRepository.save(createdCourse).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(Long id) {
        courseRepository.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Course updatedCourse) {
        Course course = getById(id);

        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        course.setPrice(updatedCourse.getPrice());
        course.setCategory(updatedCourse.getCategory());

        return courseRepository.save(course).getId();
    }


}
