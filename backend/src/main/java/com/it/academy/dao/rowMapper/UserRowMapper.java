package com.it.academy.dao.rowMapper;

import com.it.academy.enums.Role;
import com.it.academy.enums.UserStatus;
import com.it.academy.models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFullName("full_name");
        user.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setStatus(UserStatus.valueOf(rs.getString("status")));
        user.setActivationToken(rs.getString("activation_token"));
        user.setResetToken(rs.getString("reset_token"));
        user.setResetTokenExpireTime(rs.getTimestamp("reset_token_expire_time").toLocalDateTime());
        user.setStripeAccountId(rs.getString("stripe_account_id"));

        return user;
    }
}
