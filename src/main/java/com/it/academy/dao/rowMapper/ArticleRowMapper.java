package com.it.academy.dao.rowMapper;

import com.it.academy.entities.Article;
import com.it.academy.entities.Lesson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setId(rs.getLong("id"));
        article.setTitle(rs.getString("title"));
        article.setText(rs.getString("text"));

        Lesson lesson = new Lesson();
        lesson.setId(rs.getLong("lesson_id"));
        article.setLesson(lesson);

        return article;
    }
}
