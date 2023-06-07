package com.it.academy.dao;

import com.it.academy.dao.rowMapper.LessonRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Lesson;
import com.it.academy.entities.Section;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
class LessonDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private DaoValidate daoValidate;

    @InjectMocks
    private LessonDao lessonDao;

    @Test
    void testGetLessonsBySection() {
        Section section = Section.builder().id(1L).build();
        List<Lesson> expectedLessons = Arrays.asList(
                Lesson.builder().id(1L).title("Lesson 1").section(section).build(),
                Lesson.builder().id(2L).title("Lesson 2").section(section).build()
        );

        doNothing().when(daoValidate).checkSectionExistsById(section.getId());
        when(jdbcTemplate.query(anyString(), any(LessonRowMapper.class), eq(section.getId())))
                .thenReturn(expectedLessons);

        List<Lesson> actualLessons = lessonDao.getLessonsBySection(section.getId());

        assertThat(actualLessons).isEqualTo(expectedLessons);
        verify(jdbcTemplate, times(1)).query(anyString(), any(LessonRowMapper.class), eq(section.getId()));
    }

}