package com.it.academy.dao;

import com.it.academy.dao.rowMapper.SubscriptionRowMapper;
import com.it.academy.entities.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SubscriptionDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        String sql = "SELECT * FROM subscriptions WHERE user_id = ?";
        return jdbcTemplate.query(sql, new SubscriptionRowMapper(), userId);
    }

}
