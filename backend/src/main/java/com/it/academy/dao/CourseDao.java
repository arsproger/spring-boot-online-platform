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

    public List<Course> filterByPriceAsk() {
        return jdbcTemplate.query("SELECT * FROM courses ORDER BY price",
                new BeanPropertyRowMapper<>(Course.class));
    }

    public List<Course> filterByPriceDesc() {
        return jdbcTemplate.query("SELECT * FROM courses ORDER BY price desc",
                new BeanPropertyRowMapper<>(Course.class));
    }

    public List<Course> getByLanguage(String language) {
        return jdbcTemplate.query("SELECT * FROM Courses WHERE language = ?",
                new Object[]{language}, new BeanPropertyRowMapper<>(Course.class));
    }

}
