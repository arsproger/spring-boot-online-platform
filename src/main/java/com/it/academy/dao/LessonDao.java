package com.it.academy.dao;

import com.it.academy.dao.rowMapper.LessonRowMapper;
import com.it.academy.entities.Lesson;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LessonDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Lesson> getLessonsBySection(Long sectionId) {
        return jdbcTemplate.query("SELECT * FROM lessons WHERE section_id = ?",
                new LessonRowMapper(), sectionId);
    }

    public void setVideoUrl(String imageUrl, Long lessonId) {
        jdbcTemplate.update("UPDATE lessons set video_url = ? where id = ?", imageUrl, lessonId);
    }

}
