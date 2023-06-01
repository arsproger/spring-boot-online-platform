package com.it.academy.dao;

import com.it.academy.dao.validate.DaoValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public void addCourseToCart(Long userId, Long courseId) {
        daoValidate.checkUserExistsById(userId);
        daoValidate.checkCourseExistsById(courseId);
        jdbcTemplate.update("INSERT INTO carts_courses (cart_id, course_id) VALUES ((SELECT cart_id FROM users WHERE id = ?), ?);", userId, courseId);
    }

    public void removeCourseFromCart(Long userId, Long courseId) {
        daoValidate.checkUserExistsById(userId);
        daoValidate.checkCourseExistsById(courseId);
        jdbcTemplate.update("DELETE FROM carts_courses WHERE cart_id = (SELECT cart_id FROM users WHERE id = ?) AND course_id = ?;", userId, courseId);
    }
}
