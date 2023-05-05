package com.it.academy.dao;

import com.it.academy.models.Category;
import com.it.academy.models.Course;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@AllArgsConstructor
public class CourseDao {
    private final JdbcTemplate jdbcTemplate;
    public List<Course> getAllByUserId(Long userId) {
        String sql = "select * from courses join subscriptions s on courses.id = s.course_id " +
                "join categories c on c.id = courses.category_id where s.user_id = ?;";
        return jdbcTemplate.query(sql, new CourseRowMapper(), userId);
    }

    private static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getLong("id"));
            course.setName(rs.getString("name"));
            course.setDescription(rs.getString("description"));
            course.setPrice(rs.getBigDecimal("price"));
            Category category = new Category();
            category.setId(rs.getLong("category_id"));
            category.setTitle(rs.getString("title"));
            course.setCategory(category);
            course.setAmountStudents(rs.getInt("amount_students"));
            return course;
        }
    }
}

