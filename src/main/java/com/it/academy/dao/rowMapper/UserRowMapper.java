package com.it.academy.dao.rowMapper;

import com.it.academy.entities.User;
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
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setImageUrl(rs.getString("image_url"));
        user.setStripeAccountId(rs.getString("stripe_account_id"));

        return user;
    }

}
