package com.it.academy.services.impl;

import com.it.academy.models.Course;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
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
        course.setReviews(updatedCourse.getReviews());
        course.setSubscriptions(updatedCourse.getSubscriptions());
        course.setSections(updatedCourse.getSections());
        return repo.save(course).getId();
    }

    @Override
    public List<Course> getByAuthorId(Long id) {
        return repo.findByAuthorId(id);
    }


    @Override
    public List<Course> filterByPriceAsk() {
        return repo.findAll(Sort.by("price").ascending());
    }

    @Override
    public List<Course> filterByPriceDesc() {
        return repo.findAll(Sort.by("price").descending());
    }

    @Override
    public List<Course> getByLanguage(String language) {
        return repo.findByLanguage(language);
    }

}
