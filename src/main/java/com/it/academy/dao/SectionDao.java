package com.it.academy.dao;

import com.it.academy.dao.rowMapper.SectionRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SectionDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public List<Section> getSectionByCourseId(Long courseId) {
        daoValidate.checkCourseExistsById(courseId);
        return jdbcTemplate.query("SELECT * FROM sections WHERE course_id = ?",
                new SectionRowMapper(), courseId);
    }

}
