package com.example.springbootonlineplatform.services;

import com.example.springbootonlineplatform.models.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseService {
    Course getById(Long id);
    List<Course> getAll();
    Long save(Course course);
    void deleteById(Long id);
    Long update(Long id, Course course);
}
