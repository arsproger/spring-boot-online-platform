package com.it.academy.dao;

import com.it.academy.dao.rowMapper.ArticleRowMapper;
import com.it.academy.dao.validate.DaoValidate;
import com.it.academy.entities.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleDao {
    private final JdbcTemplate jdbcTemplate;
    private final DaoValidate daoValidate;

    public List<Article> getArticlesByLessonId(Long lessonId) {
        daoValidate.checkLessonExistsById(lessonId);
        return jdbcTemplate.query("SELECT * FROM articles WHERE lesson_id = ?",
                new ArticleRowMapper(), lessonId);
    }

}
