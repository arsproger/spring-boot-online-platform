package com.it.academy.dao;

import com.it.academy.models.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SubscriptionDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Subscription> getActiveSubscriptionsByUserId(Long userId) {
        String sql = "SELECT * FROM subscriptions WHERE user_id = ?";
        return jdbcTemplate.queryForList(sql, Subscription.class, userId);
    }

}
