package com.it.academy.services.impl;

import com.it.academy.models.Article;
import com.it.academy.models.Lesson;
import com.it.academy.repositories.ArticleRepository;
import com.it.academy.services.ArticleService;
import com.it.academy.services.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final LessonService lessonService;

    @Override
    public Article getById(Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Article not found with id: " + id));
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Long save(Article article) {
        return articleRepository.save(article).getId();
    }
    @Override
    public Long create(Article article, Long lessonId) {
        Article createdArticle = Article.builder()
                .lesson(lessonService.getById(lessonId))
                .title(article.getTitle())
                .text(article.getText())
                .build();
        return articleRepository.save(createdArticle).getId();
    }

    @Override
    public Long deleteById(Long id) {
        articleRepository.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Article updatedArticle) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Article not found with id: " + id));

        article.setText(updatedArticle.getText());
        article.setTitle(updatedArticle.getTitle());

        return articleRepository.save(article).getId();
    }

}
