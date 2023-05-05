package com.it.academy.dao;

import com.it.academy.models.Lesson;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LessonDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Lesson> filterByDuration(Integer from, Integer to) {
        return jdbcTemplate.queryForList("SELECT * FROM lessons WHERE duration between ? and ?",
                Lesson.class, from, to);
    }

}
