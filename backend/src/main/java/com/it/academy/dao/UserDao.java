package com.it.academy.dao;

import com.it.academy.models.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getUserByCourseId(Long courseId) {
        return jdbcTemplate.query("SELECT name, surname, date_of_birth, email FROM users " +
                "JOIN subscriptions ON(users.id = subscriptions.user_id) " +
                "WHERE subscriptions.course_id = ?", new Object[]{courseId},
                new BeanPropertyRowMapper<>(User.class));
    }

}
