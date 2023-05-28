package com.it.academy.services;

import com.it.academy.entities.Article;

import java.util.List;

public interface ArticleService {
    Article getById(Long id);

    Long create(Long userId, Article article, Long lessonId);

    Long deleteById(Long userId, Long id);

    Long update(Long userId, Long id, Article updatedArticle);

    List<Article> getArticlesByLessonId(Long lessonId);
}
