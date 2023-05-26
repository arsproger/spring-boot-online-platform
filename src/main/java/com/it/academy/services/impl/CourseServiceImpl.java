package com.it.academy.services.impl;

import com.it.academy.exceptions.AppException;
import com.it.academy.enums.Role;
import com.it.academy.models.Course;
import com.it.academy.models.User;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.services.CategoryService;
import com.it.academy.services.CourseService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repo;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public Course getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new AppException("Course not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Course> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Long authorId, Long categoryId, Course course) {
        course.setAuthor(userService.getById(authorId));
        course.setCategory(categoryService.getById(categoryId));
        course.setCreated(LocalDate.now());
        return repo.save(course).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Course updatedCourse) {
        Course course = getById(id);

        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        course.setPrice(updatedCourse.getPrice());
        course.setCategory(updatedCourse.getCategory());

        return repo.save(course).getId();
    }

}
