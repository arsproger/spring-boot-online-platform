package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CourseRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public List<Course> getByAuthorId(Long authorId) {
        daoValidate.checkUserExistsById(authorId);
        return jdbcTemplate.query("SELECT * FROM courses WHERE author_id = ?",
                new CourseRowMapper(), authorId);
    }

    public List<Course> filterByPriceAsk() {
        return jdbcTemplate.query("SELECT * FROM courses ORDER BY price",
                new CourseRowMapper());
    }

    public List<Course> filterByPriceDesc() {
        return jdbcTemplate.query("SELECT * FROM courses ORDER BY price desc",
                new CourseRowMapper());
    }

    public List<Course> getByLanguage(String language) {
        return jdbcTemplate.query("SELECT * FROM Courses WHERE language ILIKE ?",
                new CourseRowMapper(), ("%" + language + "%"));
    }

    public List<Course> getCoursesByUserCart(Long userId) {
        daoValidate.checkUserExistsById(userId);
        return jdbcTemplate.query("SELECT * FROM courses WHERE id IN " +
                        "(SELECT course_id FROM carts_courses WHERE cart_id IN " +
                        "(SELECT cart_id FROM users WHERE id = ?))",
                new CourseRowMapper(), userId);
    }

    public List<Course> getCourseByCategoryId(Long categoryId) {
        daoValidate.checkCategoryExistsById(categoryId);
        return jdbcTemplate.query("SELECT * FROM courses WHERE category_id = ?", new CourseRowMapper(), categoryId);
    }

    public List<Course> getCourseByName(String name) {
        return jdbcTemplate.query("SELECT id, name, description, price, language, created FROM courses WHERE name ILIKE ?",
                new CourseRowMapper(), ("%" + name + "%"));
    }

    public Double getCourseDurationSum(Long courseId) {
        daoValidate.checkCourseExistsById(courseId);
        return jdbcTemplate.queryForObject("SELECT sum(duration) FROM lessons " +
                "JOIN sections ON(lessons.section_id = sections.id) " +
                "WHERE sections.course_id = ?", Double.class, courseId);
    }

    public void setImageUrl(String imageUrl, Long courseId) {
        daoValidate.checkCourseExistsById(courseId);
        jdbcTemplate.update("UPDATE courses set image_url = ? where id = ?", imageUrl, courseId);
    }

}
