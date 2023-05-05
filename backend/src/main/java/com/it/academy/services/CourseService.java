package com.it.academy.services;

import com.it.academy.models.Course;

import java.util.List;

public interface CourseService {
    Course getById(Long id);

    List<Course> getAll();

    Long save(Course course);

    Long deleteById(Long id);

    Long update(Long id, Course course);
    List<Course> getAllByUserId(Long userId);
    List<Course> getByCategoryId(Long categoryId);
    List<Course> getByCategoryTitle(String title);
    List<Course> getByName(String name);

}
