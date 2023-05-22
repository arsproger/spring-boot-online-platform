package com.it.academy.controllers;

import com.it.academy.dto.ArticleDto;
import com.it.academy.mappers.ArticleMapper;
import com.it.academy.services.ArticleService;
import com.it.academy.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService service;
    private final ArticleMapper mapper;
    private final ObjectValidator<ArticleDto> validator;

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        List<ArticleDto> articles = mapper.map(service.getAll());
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id) {
        ArticleDto article = mapper.map(service.getById(id));
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping("/create/{lessonId}")
    public ResponseEntity<?> createArticle(@RequestBody ArticleDto article, @PathVariable Long lessonId) {
        if (!validator.validate(article).isEmpty()) return new ResponseEntity<>(validator.validate(article), HttpStatus.BAD_REQUEST);
        Long id = service.create(mapper.map(article), lessonId);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteArticleById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticleById(@PathVariable Long id, @RequestBody ArticleDto article) {
        if (!validator.validate(article).isEmpty()) return new ResponseEntity<>(validator.validate(article), HttpStatus.BAD_REQUEST);
        Long updatedId = service.update(id, mapper.map(article));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
