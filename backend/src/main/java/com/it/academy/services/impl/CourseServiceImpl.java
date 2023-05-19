package com.it.academy.services.impl;

import com.it.academy.models.Course;
import com.it.academy.models.User;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.services.CourseService;
import com.it.academy.services.PaymentService;
import com.it.academy.services.UserService;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.it.academy.enums.Role.ROLE_TRAINER;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserService userService;

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course not found with id: " + id));
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Long create(Course course, Long authorId) {
        User author = userService.getById(authorId);
        author.setRole(ROLE_TRAINER);
        userService.save(author);

        Course createdCourse = Course.builder()
                .author(author)
                .category(course.getCategory())
                .name(course.getName())
                .description(course.getDescription())
                .language(course.getLanguage())
                .sections(course.getSections())
                .reviews(course.getReviews())
                .price(course.getPrice())
                .subscriptions(course.getSubscriptions())
                .build();

        if (author.getStripeAccountId() == null || author.getStripeAccountId().isEmpty()) {
            return null;
        }

        return courseRepository.save(createdCourse).getId();
    }

    @Override
    public Long deleteById(Long id) {
        courseRepository.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Course updatedCourse) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Course not found with id: " + id));

        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());
        course.setPrice(updatedCourse.getPrice());
        course.setCategory(updatedCourse.getCategory());

        return courseRepository.save(course).getId();
    }

}
