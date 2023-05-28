package com.it.academy.dao;

import com.it.academy.dao.rowMapper.UserRowMapper;
import com.it.academy.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getUserByCourseId(Long courseId) {
        return jdbcTemplate.query("SELECT * FROM users " +
                        "JOIN subscriptions ON(users.id = subscriptions.user_id) " +
                        "WHERE subscriptions.course_id = ?",
                new UserRowMapper(), courseId);
    }

    public void setImageUrl(String imageUrl, Long userId) {
        jdbcTemplate.update("UPDATE USERS set image_url = ? where id = ?", imageUrl, userId);
    }

}
