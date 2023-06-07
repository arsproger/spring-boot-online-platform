package com.it.academy.dao.rowMapper;

import com.it.academy.entities.Review;
import com.it.academy.entities.User;
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
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setImageUrl(rs.getString("image_url"));
        review.setUser(user);
        return review;
    }

}
