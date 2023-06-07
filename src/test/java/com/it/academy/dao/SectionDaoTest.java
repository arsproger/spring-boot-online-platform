package com.it.academy.dao;

import com.it.academy.dao.rowMapper.SectionRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Course;
import com.it.academy.entities.Section;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class SectionDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private DaoValidate daoValidate;

    @InjectMocks
    private SectionDao sectionDao;

    @Test
    public void testGetSectionByCourseId() {
        Course course = Course.builder().id(1L).build();
        Long courseId = 1L;
        Section section1 = Section.builder().id(1L).name("Section 1").course(course).build();
        Section section2 = Section.builder().id(2L).name("Section 2").course(course).build();
        List<Section> expectedSections = Arrays.asList(section1, section2);

        doNothing().when(daoValidate).checkCourseExistsById(course.getId());
        when(jdbcTemplate.query(anyString(), any(SectionRowMapper.class), eq(courseId))).thenReturn(expectedSections);

        List<Section> actualSections = sectionDao.getSectionByCourseId(courseId);

        assertEquals(expectedSections, actualSections);
        verify(jdbcTemplate).query(anyString(), any(SectionRowMapper.class), eq(courseId));
    }

}
