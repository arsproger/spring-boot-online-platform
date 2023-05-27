package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CourseRowMapper;
import com.it.academy.models.Course;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CourseDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Course> getByAuthorId(Long id) {
        return jdbcTemplate.query("SELECT * FROM courses WHERE author_id = ?",
                new CourseRowMapper(), id);
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
        return jdbcTemplate.query("SELECT * FROM Courses WHERE language = ?",
                new CourseRowMapper(), language);
    }

    public List<Course> getCoursesByUserCart(Long userId) {
        return jdbcTemplate.query("SELECT * FROM courses WHERE id IN " +
                        "(SELECT course_id FROM carts_courses WHERE cart_id IN " +
                        "(SELECT cart_id FROM users WHERE id = ?))",
                new CourseRowMapper(), userId);
    }

    public List<Course> getCourseByCategoryId(Long categoryId) {
        return jdbcTemplate.query("SELECT * FROM courses WHERE category_id = ?",
                new CourseRowMapper(), categoryId);
    }

    public List<Course> getCourseByName(String name) {
        return jdbcTemplate.query("SELECT id, name, description, price, language, created FROM courses WHERE name = ?",
                new CourseRowMapper(), name);
    }

    public Double getCourseDurationSum(Long courseId) {
        return jdbcTemplate.queryForObject("SELECT sum(duration) FROM lessons " +
                "JOIN sections ON(lessons.section_id = sections.id) " +
                "WHERE sections.course_id = ?", Double.class, courseId);
    }

    public void setImageUrl(String imageUrl, Long courseId) {
        jdbcTemplate.update("UPDATE courses set image_url = ? where id = ?", imageUrl, courseId);
    }

}
