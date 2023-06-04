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

    List<Course> getCoursesByLanguage(String language, Long categoryId);

    List<Course> getCoursesByName(String name);

    List<Course> filterByPriceAsk(Long categoryId);

    List<Course> filterByPriceDesc(Long categoryId);

    List<Course> getCoursesByAuthor(Long authorId);

    Integer getCountOfAllCourses();

}
