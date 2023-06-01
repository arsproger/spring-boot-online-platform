package com.it.academy.dao.validate;

import com.it.academy.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DaoValidate {
    private final JdbcTemplate jdbcTemplate;

    public void checkUserById(Long userId) {
        Boolean check = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM users WHERE id = ?)", Boolean.class, userId);
        if (Boolean.FALSE.equals(check))
            throw new AppException("User not found with id: " + userId, HttpStatus.NOT_FOUND);
    }

    public void checkCourseById(Long courseId) {
        Boolean check = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM courses WHERE id = ?)", Boolean.class, courseId);
        if (Boolean.FALSE.equals(check))
            throw new AppException("Course not found with id: " + courseId, HttpStatus.NOT_FOUND);
    }

    public void checkLessonById(Long lessonId) {
        Boolean check = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM lessons WHERE id = ?)", Boolean.class, lessonId);
        if (Boolean.FALSE.equals(check))
            throw new AppException("Lesson not found with id: " + lessonId, HttpStatus.NOT_FOUND);
    }

    public void checkCategoryById(Long categoryId) {
        Boolean check = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM categories WHERE id = ?)", Boolean.class, categoryId);
        if (Boolean.FALSE.equals(check))
            throw new AppException("Category not found with id: " + categoryId, HttpStatus.NOT_FOUND);
    }

    public void checkSectionById(Long sectionId) {
        Boolean check = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM sections WHERE id = ?)", Boolean.class, sectionId);
        if (Boolean.FALSE.equals(check))
            throw new AppException("Section not found with id: " + sectionId, HttpStatus.NOT_FOUND);
    }

}