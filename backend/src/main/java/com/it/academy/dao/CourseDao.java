package com.it.academy.dao;

import com.it.academy.models.Course;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CourseDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Course> getByAuthorId(Long id) {
        return jdbcTemplate.query("SELECT * FROM Courses WHERE author_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Course.class));
    }

}
