package com.it.academy.dao.rowMapper;

import com.it.academy.models.Comment;
import com.it.academy.models.Lesson;
import com.it.academy.models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();
        comment.setId(rs.getLong("id"));
        comment.setTitle(rs.getString("title"));
        comment.setDescription(rs.getString("description"));
        comment.setDate(rs.getDate("date").toLocalDate());

        User user = new User();
        user.setId(rs.getLong("user_id"));
        comment.setUser(user);

        Lesson lesson = new Lesson();
        lesson.setId(rs.getLong("lesson_id"));
        comment.setLesson(lesson);

        return comment;
    }
}
