package com.it.academy.dao.rowMapper;

import com.it.academy.models.Course;
import com.it.academy.models.Review;
import com.it.academy.models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class ReviewRowMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setId(rs.getLong("id"));
        review.setTitle(rs.getString("title"));
        review.setDescription(rs.getString("description"));
        review.setGrade(rs.getDouble("grade"));
        review.setDate(rs.getObject("date", LocalDate.class));
        review.setUser(rs.getObject("user_id", User.class));
        review.setCourse(rs.getObject("course_id", Course.class));
        return review;
    }
}