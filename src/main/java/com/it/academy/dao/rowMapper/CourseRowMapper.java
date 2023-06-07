package com.it.academy.dao.rowMapper;

import com.it.academy.entities.Course;
import com.it.academy.entities.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));
        course.setPrice(rs.getBigDecimal("price"));
        course.setLanguage(rs.getString("language"));
        course.setCreated(rs.getDate("created").toLocalDate());
        course.setImageUrl(rs.getString("image_url"));
        User author = new User();
        author.setId(rs.getLong("author_id"));
        author.setFullName(rs.getString("full_name"));
        author.setStripeAccountId(rs.getString("stripe_account_id"));
        course.setAuthor(author);

        return course;
    }

}
