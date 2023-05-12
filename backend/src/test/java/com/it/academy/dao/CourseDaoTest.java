package com.it.academy.dao;

import com.it.academy.models.Course;
import com.it.academy.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CourseDao courseDao;

    @Test
    public void testGetByAuthorId() {
        User author = User.builder().id(1L).name("Bob").build();
        List<Course> courses = new ArrayList<>();
        courses.add(Course.builder().id(1L).name("Course 1").author(author).build());
        courses.add(Course.builder().id(2L).name("Course 2").author(author).build());
        courses.add(Course.builder().id(3L).name("Course 3").author(author).build());

        when(jdbcTemplate.query(anyString(), any(Object[].class),
                any(BeanPropertyRowMapper.class))).thenReturn(courses);

        List<Course> result = courseDao.getByAuthorId(author.getId());

        assertThat(3).isEqualTo(result.size());
        assertThat(courses.get(0)).isEqualTo(result.get(0));
        assertThat(courses.get(1)).isEqualTo(result.get(1));
        assertThat(courses.get(2)).isEqualTo(result.get(2));

        verify(jdbcTemplate).query(anyString(), any(Object[].class),
                any(BeanPropertyRowMapper.class));
    }

}
