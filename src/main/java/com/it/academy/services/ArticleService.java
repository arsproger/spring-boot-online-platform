package com.it.academy.services;

import com.it.academy.models.Article;

import java.util.List;

public interface ArticleService {
    Article getById(Long id);

    List<Article> getAll();

    Long save(Article article, Long lessonId);

    Long deleteById(Long id);

    Long update(Long id, Article article);
    List<Article> getArticlesByLessonId(Long lessonId);
}
