package com.it.academy.services.impl;

import com.it.academy.dao.ArticleDao;
import com.it.academy.entities.Article;
import com.it.academy.entities.Lesson;
import com.it.academy.entities.User;
import com.it.academy.exceptions.AppException;
import com.it.academy.repositories.ArticleRepository;
import com.it.academy.services.ArticleService;
import com.it.academy.services.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final LessonService lessonService;
    private final ArticleDao articleDao;

    @Override
    public Article getById(Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new AppException("Article not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Long create(Long userId, Article article, Long lessonId) {
        Lesson lesson = lessonService.getById(lessonId);
        User author = lesson.getSection().getCourse().getAuthor();

        if (!userId.equals(author.getId())) {
            throw new AccessDeniedException("You can't create article for this course!");
        }

        Article createdArticle = Article.builder()
                .lesson(lesson)
                .title(article.getTitle())
                .text(article.getText())
                .build();
        return articleRepository.save(createdArticle).getId();
    }

    @Override
    public Long deleteById(Long userId, Long id) {
        Article article = getById(id);
        User author = article.getLesson().getSection().getCourse().getAuthor();

        if (!userId.equals(author.getId())) {
            throw new AccessDeniedException("You can't delete this article!");
        }

        articleRepository.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long userId, Long id, Article updatedArticle) {
        Article article = getById(id);
        User author = article.getLesson().getSection().getCourse().getAuthor();

        if (!userId.equals(author.getId())) {
            throw new AccessDeniedException("You can't update this article!");
        }

        article.setText(updatedArticle.getText());
        article.setTitle(updatedArticle.getTitle());

        return articleRepository.save(article).getId();
    }

    @Override
    public List<Article> getArticlesByLessonId(Long lessonId) {
        return articleDao.getArticlesByLessonId(lessonId);
    }

}
