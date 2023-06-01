package com.it.academy.dao;

import com.it.academy.dao.rowMapper.LessonRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public List<Lesson> getLessonsBySection(Long sectionId) {
        daoValidate.checkSectionExistsById(sectionId);
        return jdbcTemplate.query("SELECT * FROM lessons WHERE section_id = ?",
                new LessonRowMapper(), sectionId);
    }

    public void setVideoUrl(String imageUrl, Long lessonId) {
        daoValidate.checkLessonExistsById(lessonId);
        jdbcTemplate.update("UPDATE lessons set video_url = ? where id = ?", imageUrl, lessonId);
    }

}
