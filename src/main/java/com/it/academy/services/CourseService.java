package com.it.academy.services;

import com.it.academy.entities.Course;

import java.util.List;

public interface CourseService {
    Course getById(Long id);

    List<Course> getAll();

    Long create(Long authorId, Long categoryId, Course course);

    Long deleteById(Long userId, Long courseId);

    Long update(Long userId, Long courseId, Course updatedCourse);

    Double getCourseDuration(Long courseId);

    List<Course> getCoursesByCategory(Long categoryId);

    List<Course> getCoursesByLanguage(String language);

    List<Course> getCoursesByName(String name);

    List<Course> filterByPriceAsk();

    List<Course> filterByPriceDesc();

    List<Course> getCoursesByAuthor(Long authorId);
}
