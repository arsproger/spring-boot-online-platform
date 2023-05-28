package com.it.academy.dao;

import com.it.academy.dao.rowMapper.ArticleRowMapper;
import com.it.academy.entities.Article;
import com.it.academy.entities.Lesson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ArticleDaoTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ArticleDao articleDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetArticlesByLessonId() {
        Lesson lesson = Lesson.builder().id(1L).build();
        List<Article> expectedArticles = Arrays.asList(
                new Article(1L, "Title 1", "Content 1", lesson),
                new Article(2L, "Title 2", "Content 2", lesson)
        );

        when(jdbcTemplate.query(anyString(), any(ArticleRowMapper.class), eq(lesson.getId())))
                .thenReturn(expectedArticles);

        List<Article> actualArticles = articleDao.getArticlesByLessonId(lesson.getId());

        assertThat(actualArticles).isEqualTo(expectedArticles);
    }
}
