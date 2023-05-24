package com.it.academy.dao;

import com.it.academy.models.Section;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SectionDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Section> getSectionByCourseId(Long courseId) {
        return jdbcTemplate.queryForList("SELECT * FROM sections WHERE course_id = ?",
                Section.class, courseId);
    }

}
