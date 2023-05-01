package com.example.springbootonlineplatform.services.impl;

import com.example.springbootonlineplatform.models.Article;
import com.example.springbootonlineplatform.repositories.ArticleRepository;
import com.example.springbootonlineplatform.services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Long update(Long id, Article updatedArticle) {
        Article article = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Article not found with id: " + id));

        article.setComments(updatedArticle.getComments());
        article.setText(updatedArticle.getText());
        article.setTitle(updatedArticle.getTitle());
        article.setCourse(updatedArticle.getCourse());
        article.setComments(updatedArticle.getComments());
        return id;
    }
}
