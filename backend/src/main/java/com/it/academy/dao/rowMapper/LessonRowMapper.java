package com.it.academy.dao.rowMapper;

import com.it.academy.models.Lesson;
import com.it.academy.models.Section;
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
        lesson.setDuration(rs.getDouble("duration"));

        Section section = new Section();
        section.setId(rs.getLong("section_id"));
        lesson.setSection(section);

        return lesson;
    }
}
