package com.it.academy.dao.rowMapper;

import com.it.academy.models.Category;
import com.it.academy.models.Course;
import com.it.academy.models.User;
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

        Category category = new Category();
        category.setId(rs.getLong("category_id"));
        course.setCategory(category);

        User author = new User();
        author.setId(rs.getLong("author_id"));
        course.setAuthor(author);

        return course;
    }
}
