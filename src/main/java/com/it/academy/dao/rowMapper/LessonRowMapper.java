package com.it.academy.dao.rowMapper;

import com.it.academy.entities.Lesson;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LessonRowMapper implements RowMapper<Lesson> {
    @Override
    public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setId(rs.getLong("id"));
        lesson.setTitle(rs.getString("title"));
        lesson.setDescription(rs.getString("description"));
        lesson.setVideoUrl(rs.getString("video_url"));
        lesson.setDuration(rs.getDouble("duration"));

        return lesson;
    }

}
