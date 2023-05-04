package com.it.academy.services;

import com.it.academy.models.Article;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleService {
    Article getById(Long id);
    List<Article> getAll();
    Long save(Article article);
    Long deleteById(Long id);
    Long update(Long id, Article article);
}
