package com.it.academy.dao.rowMapper;

import com.it.academy.models.Course;
import com.it.academy.models.Section;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SectionRowMapper implements RowMapper<Section> {
    @Override
    public Section mapRow(ResultSet rs, int rowNum) throws SQLException {
        Section section = new Section();
        section.setId(rs.getLong("id"));
        section.setName(rs.getString("name"));

        Course course = new Course();
        course.setId(rs.getLong("course_id"));
        section.setCourse(course);

        return section;
    }
}
