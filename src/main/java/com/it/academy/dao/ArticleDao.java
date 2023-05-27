package com.it.academy.dao;

import com.it.academy.dao.rowMapper.ArticleRowMapper;
import com.it.academy.dao.rowMapper.CommentRowMapper;
import com.it.academy.models.Article;
import com.it.academy.models.Comment;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ArticleDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Article> getArticlesByLessonId(Long id) {
        return jdbcTemplate.query("SELECT * FROM articles WHERE lesson_id = ?",
                new ArticleRowMapper(), id);
    }
}
