package com.it.academy.services.impl;

import com.it.academy.models.Article;
import com.it.academy.repositories.ArticleRepository;
import com.it.academy.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repo;

    @Override
    public Article getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Article not found with id: " + id));
    }

    @Override
    public List<Article> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Article article) {
        return repo.save(article).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Article updatedArticle) {
        Article article = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Article not found with id: " + id));

        article.setText(updatedArticle.getText());
        article.setTitle(updatedArticle.getTitle());

        return repo.save(article).getId();
    }

}
