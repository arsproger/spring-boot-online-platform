package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CommentRowMapper;
import com.it.academy.models.Comment;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;
    public List<Comment> getCourseCommentsByAuthorId(Long authorId) {
        String sql = "select * from comments join courses_comments cc on comments.id = cc.comments_id " +
                "join courses c on c.id = cc.course_id where c.author_id = ?;";
        return jdbcTemplate.query(sql, new CommentRowMapper(), authorId);
    }

    public List<Comment> getCommentsByCourseId(Long courseId) {
        String sql = "select * from comments join courses_comments cc on comments.id = cc.comments_id " +
                "join courses on courses.id = cc.course_id where courses.id = ?;";
        return jdbcTemplate.query(sql, new CommentRowMapper(), courseId);
    }

}
