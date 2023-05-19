package com.it.academy.services;

import com.it.academy.models.Course;

import java.util.List;

public interface CourseService {
    Course getById(Long id);

    List<Course> getAll();

    Long create(Course course, Long authorId);

    Long deleteById(Long id);

    Long update(Long id, Course course);

}
