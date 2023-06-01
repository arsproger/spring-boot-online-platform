package com.it.academy.dao;

import com.it.academy.dao.rowMapper.CommentRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public List<Comment> getCommentsByLessonId(Long lessonId) {
        daoValidate.checkLessonExistsById(lessonId);
        return jdbcTemplate.query("SELECT * FROM comments WHERE lesson_id = ?",
                new CommentRowMapper(), lessonId);
    }
}
