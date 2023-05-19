package com.it.academy.dao;

import com.it.academy.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDao userDao;

    @Test
    public void testGetUserByCourseId() {
        // Mocking the query results
        List<User> expectedUsers = new ArrayList<>();
        User user1 = User.builder()
                .name("Arsen")
                .build();
        User user2 = User.builder()
                .name("Bob")
                .build();
        expectedUsers.add(user1);
        expectedUsers.add(user2);

        when(jdbcTemplate.queryForList(anyString(), User.class, anyLong())).thenReturn(expectedUsers);

        // Calling the actual method
        List<User> actualUsers = userDao.getUserByCourseId(123L);

        // Verifying the interactions
        verify(jdbcTemplate).queryForList("SELECT name, surname, date_of_birth, email FROM users " +
                "JOIN subscriptions ON(users.id = subscriptions.user_id) " +
                "WHERE subscriptions.course_id = ?", User.class, 123L);

        // Asserting the result
        assertEquals(expectedUsers, actualUsers);
    }

}
