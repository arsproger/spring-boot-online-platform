package com.it.academy.dao;

import com.it.academy.dao.rowMapper.UserRowMapper;
import com.it.academy.entities.Course;
import com.it.academy.entities.Subscription;
import com.it.academy.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDao userDao;

    @Test
    public void testGetUserByCourseId() {
        User user1 = User.builder().id(1L).fullName("Bob").build();
        User user2 = User.builder().id(2L).fullName("Steven").build();
        User user3 = User.builder().id(3L).fullName("Nancy").build();

        Course course = Course.builder().id(1L).name("Java").build();

        Subscription subscription1 = Subscription.builder().id(1L).user(user1).course(course).build();
        Subscription subscription2 = Subscription.builder().id(2L).user(user2).course(course).build();
        Subscription subscription3 = Subscription.builder().id(3L).user(user3).course(course).build();

        course.setSubscriptions(Arrays.asList(subscription1, subscription2, subscription3));

        List<User> expectedCourseStudents = course.getSubscriptions()
                .stream().map(Subscription::getUser).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(UserRowMapper.class), anyLong()))
                .thenReturn(expectedCourseStudents);

        List<User> actualCourseStudents = userDao.getUserByCourseId(course.getId());

        assertThat(actualCourseStudents).isEqualTo(expectedCourseStudents);

        verify(jdbcTemplate, times(1))
                .query(anyString(), any(UserRowMapper.class), anyLong());
    }

}
