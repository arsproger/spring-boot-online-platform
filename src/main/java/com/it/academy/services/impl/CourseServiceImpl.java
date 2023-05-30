package com.it.academy.services.impl;

import com.it.academy.dao.CourseDao;
import com.it.academy.entities.Course;
import com.it.academy.entities.User;
import com.it.academy.enums.Role;
import com.it.academy.exceptions.AppException;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.services.CategoryService;
import com.it.academy.services.CourseService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CourseDao courseDao;

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new AppException("Course not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Long save(Course course) {
        return courseRepository.save(course).getId();
    }

    @Override
    public Long create(Long authorId, Long categoryId, Course course) {
        User author = userService.getById(authorId);
        author.setRole(Role.ROLE_TRAINER);
        userService.save(author);

        Course createdCourse = Course.builder()
                .author(author)
                .category(categoryService.getById(categoryId))
                .name(course.getName())
                .description(course.getDescription())
                .language(course.getLanguage())
                .sections(course.getSections())
                .reviews(course.getReviews())
                .price(course.getPrice())
                .subscriptions(course.getSubscriptions())
                .created(LocalDate.now())
                .build();

        if (author.getStripeAccountId() == null || author.getStripeAccountId().isEmpty()) return null;

        return courseRepository.save(createdCourse).getId();
    }

    @Override
    public Long deleteById(Long userId, Long courseId) {
        User user = userService.getById(userId);

        if (!(getById(courseId).getAuthor().getId().equals(userId) ||
                user.getRole().equals(Role.ROLE_ADMIN))) {
            throw new AccessDeniedException("You can't delete this course!");
        }

        courseRepository.deleteById(courseId);
        return courseId;
    }

    @Override
    public Long update(Long userId, Long courseId, Course updatedCourse) {
        Course course = getById(courseId);

        if (!course.getAuthor().getId().equals(userId)) {
            throw new AccessDeniedException("You can't update this course!");
        }

        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        course.setPrice(updatedCourse.getPrice());
        course.setCategory(updatedCourse.getCategory());

        return courseRepository.save(course).getId();
    }

    @Override
    public Double getCourseDuration(Long courseId) {
        return courseDao.getCourseDurationSum(courseId);
    }

    @Override
    public List<Course> getCoursesByName(String name) {
        return courseDao.getCourseByName(name);
    }

    @Override
    public List<Course> getCoursesByLanguage(String language) {
        return courseDao.getByLanguage(language);
    }

    @Override
    public List<Course> getCoursesByCategory(Long categoryId) {
        return courseDao.getCourseByCategoryId(categoryId);
    }

    @Override
    public List<Course> getCoursesByAuthor(Long authorId) {
        return courseDao.getByAuthorId(authorId);
    }

    public List<Course> filterByPriceDesc() {
        return courseDao.filterByPriceDesc();
    }

    @Override
    public List<Course> filterByPriceAsk() {
        return courseDao.filterByPriceAsk();
    }

}
