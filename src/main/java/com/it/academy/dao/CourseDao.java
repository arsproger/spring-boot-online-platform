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
        return jdbcTemplate.query("SELECT * FROM courses join users u on u.id = courses.author_id WHERE author_id = ?",
                new CourseRowMapper(), authorId);
    }

    public List<Course> filterByPriceAsk(Long categoryId, Integer pageNumber, Integer pageSize) {
        daoValidate.checkCategoryExistsById(categoryId);
        int offset = (pageNumber - 1) * pageSize;

        return jdbcTemplate.query("SELECT * FROM courses join users u on u.id = courses.author_id " +
                        "where courses.category_id = ? ORDER BY price LIMIT ? OFFSET ?",
                new CourseRowMapper(), categoryId, pageSize, offset);
    }

    public List<Course> filterByPriceDesc(Long categoryId, Integer pageNumber, Integer pageSize) {
        daoValidate.checkCategoryExistsById(categoryId);
        int offset = (pageNumber - 1) * pageSize;

        return jdbcTemplate.query("SELECT * FROM courses join users u on u.id = courses.author_id " +
                        "where courses.category_id = ? ORDER BY price desc LIMIT ? OFFSET ?",
                new CourseRowMapper(), categoryId, pageSize, offset);
    }

    public List<Course> getByLanguage(String language, Long categoryId, Integer pageNumber, Integer pageSize) {
        daoValidate.checkCategoryExistsById(categoryId);
        int offset = (pageNumber - 1) * pageSize;

        return jdbcTemplate.query("SELECT * FROM Courses join users u on u.id = courses.author_id " +
                        "WHERE language ILIKE ? and courses.category_id = ? LIMIT ? OFFSET ?",
                new CourseRowMapper(), ("%" + language + "%"), categoryId, pageSize, offset);
    }

    public List<Course> getCoursesByUserCart(Long userId) {
        daoValidate.checkUserExistsById(userId);
        return jdbcTemplate.query("SELECT * FROM courses join users u on u.id = courses.author_id WHERE courses.id IN " +
                        "(SELECT course_id FROM carts_courses WHERE cart_id IN " +
                        "(SELECT cart_id FROM users WHERE id = ?))",
                new CourseRowMapper(), userId);
    }

    public List<Course> getCourseByCategoryId(Long categoryId, Integer pageNumber, Integer pageSize) {
        daoValidate.checkCategoryExistsById(categoryId);
        int offset = (pageNumber - 1) * pageSize;

        daoValidate.checkCategoryExistsById(categoryId);
        return jdbcTemplate.query("SELECT * FROM courses join users u on u.id = courses.author_id " +
                        "WHERE category_id = ? LIMIT ? OFFSET ?",
                new CourseRowMapper(), categoryId, pageSize, offset);
    }

    public List<Course> getCourseByName(String name) {
        return jdbcTemplate.query("SELECT * FROM courses join users u on u.id = courses.author_id WHERE name ILIKE ?",
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

    public Integer getCountOfAllCourses() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM courses", Integer.class);
    }

    public List<Course> purchasedCoursesOfTheCurrentUser(Long userId) {
        daoValidate.checkUserExistsById(userId);
        return jdbcTemplate.query("SELECT * FROM courses WHERE id IN " +
                "(SELECT course_id FROM subscriptions WHERE user_id = 1)", new CourseRowMapper(), userId);
    }

}
