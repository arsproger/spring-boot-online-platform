package com.it.academy.dao;

import com.it.academy.models.Course;
import com.it.academy.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CourseDao courseDao;

    @Test
    public void testGetByAuthorId() {
        User user = User.builder().id(1L).build();
        Course course1 = Course.builder().id(1L).author(user).build();
        Course course2 = Course.builder().id(2L).author(user).build();
        List<Course> expectedCourses = Arrays.asList(course1, course2);

        when(jdbcTemplate.queryForList(any(String.class), any(Class.class), any(Long.class)))
                .thenReturn(expectedCourses);

        List<Course> actualCourses = courseDao.getByAuthorId(user.getId());

        assertThat(actualCourses).isEqualTo(expectedCourses);
    }



}
