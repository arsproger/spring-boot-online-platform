package com.it.academy.services.impl;

import com.it.academy.dao.ArticleDao;
import com.it.academy.exceptions.AppException;
import com.it.academy.models.Article;
import com.it.academy.repositories.ArticleRepository;
import com.it.academy.services.ArticleService;
import com.it.academy.services.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repo;
    private final LessonService lessonService;
    private final ArticleDao articleDao;

    @Override
    public Article getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new AppException("Article not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Article> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Article article, Long lessonId) {
        Article createdArticle = Article.builder()
                .lesson(lessonService.getById(lessonId))
                .title(article.getTitle())
                .text(article.getText())
                .build();
        return repo.save(createdArticle).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Article updatedArticle) {
        Article article = getById(id);

        article.setText(updatedArticle.getText());
        article.setTitle(updatedArticle.getTitle());

        return repo.save(article).getId();
    }

    @Override
    public List<Article> getArticlesByLessonId(Long lessonId) {
        return articleDao.getArticlesByLessonId(lessonId);
    }

}
