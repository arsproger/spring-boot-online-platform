package com.it.academy.dao;

import com.it.academy.dao.rowMapper.LessonRowMapper;
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
        return jdbcTemplate.query("SELECT * FROM lessons WHERE duration between ? and ?",
                new LessonRowMapper(), from, to);
    }

    public void setVideoUrl(String imageUrl, Long lessonId) {
        jdbcTemplate.update("UPDATE lessons set image_url = ? where id = ?", imageUrl, lessonId);
    }

}
