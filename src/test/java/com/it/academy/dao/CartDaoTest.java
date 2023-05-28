package com.it.academy.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class CartDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CartDao cartDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCourseToCart() {
        Long userId = 1L;
        Long courseId = 1L;

        cartDao.addCourseToCart(userId, courseId);

        verify(jdbcTemplate, times(1))
                .update("INSERT INTO carts_courses (cart_id, course_id) VALUES ((SELECT cart_id FROM users WHERE id = ?), ?);",
                        userId, courseId);
    }

    @Test
    void testRemoveCourseFromCart() {
        Long userId = 1L;
        Long courseId = 1L;

        cartDao.removeCourseFromCart(userId, courseId);

        verify(jdbcTemplate, times(1))
                .update("DELETE FROM carts_courses WHERE cart_id = (SELECT cart_id FROM users WHERE id = ?) AND course_id = ?;",
                        userId, courseId);
    }
}