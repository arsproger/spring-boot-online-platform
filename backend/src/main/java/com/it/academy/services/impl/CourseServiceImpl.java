package com.it.academy.services.impl;

import com.it.academy.dao.CourseDao;
import com.it.academy.dtos.CourseDto;
import com.it.academy.models.Course;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repo;
    private final CourseDao courseDao;

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
        course.setSubscriptions(updatedCourse.getSubscriptions());
        course.setSections(updatedCourse.getSections());
        return repo.save(course).getId();
    }

    @Override
    public List<Course> getAllCoursesByUserId(Long userId) {
        return courseDao.getAllCoursesByUserId(userId);
    }

    @Override
    public List<Course> getByCategoryId(Long categoryId) {
        return repo.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Course> getCoursesByCategoryTitle(String title) {
        return courseDao.getCoursesByCategoryTitle(title);
    }

    @Override
    public List<Course> getCourseByName(String name) {
        return courseDao.getCourseByName(name);
    }

    @Override
    public List<Course> getByByAuthorId(Long authorId) {
        return repo.findAllByAuthorId(authorId);
    }

    @Override
    public Integer getUsersAmountByAuthor(Long authorId) {
        return courseDao.getUsersAmountByAuthor(authorId);
    }

    @Override
    public Integer getUsersAmountByCourse(Long courseId) {
        return courseDao.getUsersAmountByCourse(courseId);
    }
}
