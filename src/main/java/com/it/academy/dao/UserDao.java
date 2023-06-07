package com.it.academy.dao;

import com.it.academy.dao.rowMapper.UserRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public List<User> getUserByCourseId(Long courseId) {
        daoValidate.checkCourseExistsById(courseId);
        return jdbcTemplate.query("SELECT * FROM users " +
                        "JOIN subscriptions ON(users.id = subscriptions.user_id) " +
                        "WHERE subscriptions.course_id = ?",
                new UserRowMapper(), courseId);
    }

    public void setImageUrl(String imageUrl, Long userId) {
        daoValidate.checkUserExistsById(userId);
        jdbcTemplate.update("UPDATE users set image_url = ? where id = ?", imageUrl, userId);
    }

    public Integer getCountOfAllUsers() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM users", Integer.class);
    }

    public Integer getCountOfAllUsersToday() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM users WHERE created_date = current_date", Integer.class);
    }

    public void updateUserById(Long userId, User user) {
        daoValidate.checkUserExistsById(userId);
        jdbcTemplate.update("UPDATE users SET full_name = ?, date_of_birth = ? WHERE users.id = ?",
                user.getFullName(), user.getDateOfBirth(), user.getId());
    }

    public void updateUserResetTokenByEmail(String email, String resetToken, LocalDateTime resetTokenExpireTime) {
        jdbcTemplate.update("UPDATE users SET reset_token = ?, reset_token_expire_time = ? where email = ?",
                resetToken, resetTokenExpireTime, email);
    }

    public void updateUserResetTokenAfterReset(String newPassword, String resetToken) {
        jdbcTemplate.update("UPDATE users SET password = ?, reset_token = NULL, reset_token_expire_time = NULL WHERE reset_token = ?",
                newPassword, resetToken);
    }

    public Boolean coursePurchaseCheck(Long userId, Long courseId) {
        daoValidate.checkUserExistsById(userId);
        daoValidate.checkCourseExistsById(courseId);
        Integer count;

        count = jdbcTemplate.queryForObject("SELECT count(*) FROM courses WHERE author_id = ? AND id = ?",
                Integer.class, userId, courseId);
        if (count != null && count > 0) return true;

        count = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM subscriptions WHERE user_id = ? AND course_id = ?",
                Integer.class, userId, courseId);

        return count != null && count > 0;
    }

}
