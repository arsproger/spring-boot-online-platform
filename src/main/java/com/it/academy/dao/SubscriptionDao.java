package com.it.academy.dao;

import com.it.academy.dao.rowMapper.SubscriptionRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubscriptionDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        daoValidate.checkUserExistsById(userId);
        String sql = "SELECT * FROM subscriptions WHERE user_id = ?";
        return jdbcTemplate.query(sql, new SubscriptionRowMapper(), userId);
    }

}
