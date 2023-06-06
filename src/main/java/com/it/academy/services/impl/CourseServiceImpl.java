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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CourseDao courseDao;

    @Override
    @PreAuthorize("permitAll()")
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new AppException("Course not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("permitAll()")
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Long create(Long authorId, Long categoryId, Course course) {
        User author = userService.getById(authorId);
        author.setRole(Role.ROLE_TRAINER);
        userService.save(author);

        if (course.getImageUrl() == null) course.setImageUrl("default-course-image.jpg");

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
                .imageUrl(course.getImageUrl())
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
    @PreAuthorize("permitAll()")
    public Double getCourseDuration(Long courseId) {
        Double courseDurationSum = courseDao.getCourseDurationSum(courseId);
        return courseDurationSum != null ? courseDurationSum : 0.0;
    }

    @Override
    @PreAuthorize("permitAll()")
    public List<Course> getCoursesByName(String name) {
        return courseDao.getCourseByName(name);
    }

    @Override
    @PreAuthorize("permitAll()")
    public List<Course> getCoursesByLanguage(String language, Long categoryId, Integer pageNumber) {
        return courseDao.getByLanguage(language, categoryId, pageNumber);
    }

    @Override
    @PreAuthorize("permitAll()")
    public List<Course> getCoursesByCategory(Long categoryId, Integer pageNumber) {
        return courseDao.getCourseByCategoryId(categoryId, pageNumber);
    }

    @Override
    @PreAuthorize("permitAll()")
    public List<Course> getCoursesByAuthor(Long authorId) {
        return courseDao.getByAuthorId(authorId);
    }

    @Override
    @PreAuthorize("permitAll()")
    public Integer getCountOfAllCourses() {
        return courseDao.getCountOfAllCourses();
    }

    @Override
    @PreAuthorize("permitAll()")
    public List<Course> filterByPriceDesc(Long categoryId, Integer pageNumber) {
        return courseDao.filterByPriceDesc(categoryId, pageNumber);
    }

    @Override
    @PreAuthorize("permitAll()")
    public List<Course> filterByPriceAsk(Long categoryId, Integer pageNumber) {
        return courseDao.filterByPriceAsk(categoryId, pageNumber);
    }

}
