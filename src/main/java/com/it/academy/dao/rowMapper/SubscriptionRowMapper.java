package com.it.academy.dao.rowMapper;

import com.it.academy.entities.Course;
import com.it.academy.entities.Subscription;
import com.it.academy.entities.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SubscriptionRowMapper implements RowMapper<Subscription> {
    @Override
    public Subscription mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subscription subscription = new Subscription();
        Course course = new Course();
        course.setId(rs.getLong("course_id"));
        User user = new User();
        user.setId(rs.getLong("user_id"));

        subscription.setId(rs.getLong("id"));
        subscription.setCourse(course);
        subscription.setCreationDate(rs.getDate("creation_date").toLocalDate());
        subscription.setUser(user);
        return subscription;
    }

}


