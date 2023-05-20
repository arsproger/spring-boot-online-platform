package com.it.academy.services.impl;

import com.it.academy.models.Course;
import com.it.academy.repositories.CategoryRepository;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.repositories.UserRepository;
import com.it.academy.services.CourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repo;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Course getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course not found with id: " + id));
    }

    @Override
    public List<Course> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Long authorId, Long categoryId, Course course) {
        course.setAuthor(userRepository.findById(authorId).orElseThrow(
                () -> new EntityNotFoundException("User not found with id: " + authorId)));
        course.setCategory(categoryRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + categoryId)));
        return repo.save(course).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Course updatedCourse) {
        Course course = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course not found with id: " + id));

        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        course.setPrice(updatedCourse.getPrice());
        course.setCategory(updatedCourse.getCategory());

        return repo.save(course).getId();
    }

}
