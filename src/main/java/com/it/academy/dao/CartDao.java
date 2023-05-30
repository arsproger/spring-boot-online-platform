package com.it.academy.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartDao {
    private final JdbcTemplate jdbcTemplate;

    public void addCourseToCart(Long userId, Long courseId) {
        jdbcTemplate.update("INSERT INTO carts_courses (cart_id, course_id) VALUES ((SELECT cart_id FROM users WHERE id = ?), ?);", userId, courseId);
    }

    public void removeCourseFromCart(Long userId, Long courseId) {
        jdbcTemplate.update("DELETE FROM carts_courses WHERE cart_id = (SELECT cart_id FROM users WHERE id = ?) AND course_id = ?;", userId, courseId);
    }
}
