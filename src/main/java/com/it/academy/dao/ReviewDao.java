package com.it.academy.dao;

import com.it.academy.dao.rowMapper.ReviewRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public List<Review> getCourseReviewsByAuthorId(Long authorId) {
        daoValidate.checkUserExistsById(authorId);
        String sql = "select * from reviews " +
                "join courses on reviews.course_id = courses.id " +
                "where author_id = ?";
        return jdbcTemplate.query(sql, new ReviewRowMapper(), authorId);
    }

    public List<Review> getReviewsByCourseId(Long courseId) {
        daoValidate.checkCourseExistsById(courseId);
        String sql = "select * from reviews " +
                "where course_id = ?";
        return jdbcTemplate.query(sql, new ReviewRowMapper(), courseId);
    }

    public Double getCourseAvgGrade(Long courseId) {
        daoValidate.checkCourseExistsById(courseId);
        return jdbcTemplate.queryForObject("SELECT avg(grade) FROM reviews WHERE course_id = ?",
                Double.class, courseId);
    }

}
