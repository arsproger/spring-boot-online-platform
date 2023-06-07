package com.it.academy.dao;

import com.it.academy.dao.validate.DaoValidate;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.Mockito.*;

@SpringJUnitConfig
class CartDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private DaoValidate daoValidate;

    @InjectMocks
    private CartDao cartDao;

    @Test
    void testAddCourseToCart() {
        Long userId = 1L;
        Long courseId = 1L;

        doNothing().when(daoValidate).checkUserExistsById(userId);
        doNothing().when(daoValidate).checkCourseExistsById(courseId);
        when(jdbcTemplate.update(anyString(), anyLong(), anyLong())).thenReturn(0);

        cartDao.addCourseToCart(userId, courseId);

        verify(jdbcTemplate, times(1))
                .update("INSERT INTO carts_courses (cart_id, course_id) VALUES ((SELECT cart_id FROM users WHERE id = ?), ?);",
                        userId, courseId);
    }

    @Test
    void testRemoveCourseFromCart() {
        Long userId = 1L;
        Long courseId = 1L;

        doNothing().when(daoValidate).checkUserExistsById(userId);
        doNothing().when(daoValidate).checkCourseExistsById(courseId);
        when(jdbcTemplate.update(anyString(), anyLong(), anyLong())).thenReturn(0);

        cartDao.removeCourseFromCart(userId, courseId);

        verify(jdbcTemplate, times(1))
                .update("DELETE FROM carts_courses WHERE cart_id = (SELECT cart_id FROM users WHERE id = ?) AND course_id = ?;",
                        userId, courseId);
    }

}