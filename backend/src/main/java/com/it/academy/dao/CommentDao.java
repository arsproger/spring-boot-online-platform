package com.it.academy.dao;

import com.it.academy.models.Comment;
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
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Comment> getCourseCommentsByAuthorId(Long authorId) {
        String sql = "select * from reviews " +
                "join courses on(reviews.course_id = courses.id) " +
                "where author_id = ?";
        return jdbcTemplate.query(sql, new CommentRowMapper(), authorId);
    }

    public List<Comment> getCommentsByCourseId(Long courseId) {
        String sql = "select * from reviews " +
                "where course_id = ?";
        return jdbcTemplate.query(sql, new CommentRowMapper(), courseId);
    }

    private static class CommentRowMapper implements RowMapper<Comment> {
        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comment comment = new Comment();
            comment.setId(rs.getLong("id"));

            if (rs.getDate("date") != null) {
                comment.setDate(rs.getDate("date").toLocalDate());
            }
            comment.setDescription(rs.getString("description"));
            comment.setTitle(rs.getString("title"));
            User user = new User();
            user.setId(rs.getLong("user_id"));
            comment.setUser(user);
            return comment;
        }
    }
}
