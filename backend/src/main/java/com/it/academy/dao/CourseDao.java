package com.it.academy.dao;

import com.it.academy.models.Course;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CourseDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Course> getByAuthorId(Long id) {
        return jdbcTemplate.queryForList("SELECT * FROM Courses WHERE author_id = ?",
                Course.class, id);
    }

    public List<Course> filterByPriceAsk() {
        return jdbcTemplate.queryForList("SELECT * FROM courses ORDER BY price",
                Course.class);
    }

    public List<Course> filterByPriceDesc() {
        return jdbcTemplate.queryForList("SELECT * FROM courses ORDER BY price desc",
                Course.class);
    }

    public List<Course> getByLanguage(String language) {
        return jdbcTemplate.queryForList("SELECT * FROM Courses WHERE language = ?",
                Course.class, language);
    }

}
