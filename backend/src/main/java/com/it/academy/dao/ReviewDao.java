package com.it.academy.dao;

import com.it.academy.models.Review;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ReviewDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Review> getCourseCommentsByAuthorId(Long authorId) {
        String sql = "select * from reviews " +
                "join courses on(reviews.course_id = courses.id) " +
                "where author_id = ?";
        return jdbcTemplate.queryForList(sql, Review.class, authorId);
    }

    public List<Review> getCommentsByCourseId(Long courseId) {
        String sql = "select * from reviews " +
                "where course_id = ?";
        return jdbcTemplate.queryForList(sql, Review.class, courseId);
    }

    public Double getCourseAvgGrade(Long courseId) {
        return jdbcTemplate.queryForObject("SELECT avg(grade) FROM reviews WHERE course_id = ?",
                Double.class, courseId);
    }

}