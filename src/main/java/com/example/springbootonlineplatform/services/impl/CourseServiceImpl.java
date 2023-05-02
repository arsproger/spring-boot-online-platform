package com.example.springbootonlineplatform.services.impl;

import com.example.springbootonlineplatform.models.Course;
import com.example.springbootonlineplatform.repositories.CourseRepository;
import com.example.springbootonlineplatform.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repo;

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
    public Long save(Course course) {
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
        course.setCategory(updatedCourse.getCategory());
        course.setPrice(updatedCourse.getPrice());
        course.setDescription(updatedCourse.getDescription());
        course.setComments(updatedCourse.getComments());
        //course.setCarts(updatedCourse.getCarts());
        course.setSubscriptions(updatedCourse.getSubscriptions());
        course.setLessons(updatedCourse.getLessons());
        return repo.save(course).getId();
    }
}
