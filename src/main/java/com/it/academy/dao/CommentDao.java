package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CommentRowMapper;
import com.it.academy.entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Comment> getCommentsByLessonId(Long lessonId) {
        return jdbcTemplate.query("SELECT * FROM comments WHERE lesson_id = ?",
                new CommentRowMapper(), lessonId);
    }
}
