package com.it.academy.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserDao userDao;

    @Test
    public void testGetUserByCourseId() {

    }

}
