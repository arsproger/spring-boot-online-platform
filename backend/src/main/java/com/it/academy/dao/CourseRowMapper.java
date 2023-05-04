package com.it.academy.dao;

import com.it.academy.models.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));
        course.setPrice(rs.getBigDecimal("price"));
        course.setAmountStudents(rs.getInt("amount_students"));
        course.setLanguage(rs.getString("language"));

        return course;
    }
}
