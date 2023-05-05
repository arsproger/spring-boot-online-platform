package com.it.academy.dao;

import com.it.academy.models.Course;
import com.it.academy.models.Subscription;
import com.it.academy.models.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@AllArgsConstructor
public class SubscriptionDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Subscription> getActiveSubscriptionsByUserId(Long userId) {
        String sql = "SELECT * FROM subscriptions WHERE date_finish >= CURRENT_DATE OR date_finish IS NULL AND user_id =?;";
        return jdbcTemplate.query(sql, new SubscriptionRowMapper(), userId);
    }

    private static class SubscriptionRowMapper implements RowMapper<Subscription> {
        @Override
        public Subscription mapRow(ResultSet rs, int rowNum) throws SQLException {
            Subscription subscription = new Subscription();
            Course course = new Course();
            course.setId(rs.getLong("course_id"));
            User user = new User();
            user.setId(rs.getLong("user_id"));

            subscription.setId(rs.getLong("id"));
            subscription.setCourse(course);

            subscription.setDateStart(rs.getDate("date_start").toLocalDate());
            subscription.setUser(user);
            if(rs.getDate("date_finish") != null) {
                subscription.setDateFinish(rs.getDate("date_finish").toLocalDate());
            }
            return subscription;
        }
    }
}
