package com.it.academy.controllers;

import com.it.academy.dto.ArticleDto;
import com.it.academy.mappers.ArticleMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
@Tag(name = "Контроллер для статей к урокам")
public class ArticleController {
    private final ArticleService service;
    private final ArticleMapper mapper;

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<ArticleDto>> getArticlesByLessonId(@PathVariable Long lessonId) {
        List<ArticleDto> articles = mapper.map(service.getArticlesByLessonId(lessonId));
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id) {
        ArticleDto article = mapper.map(service.getById(id));
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createArticle(@AuthenticationPrincipal DetailsUser detailsUser,
                                              @RequestBody @Valid ArticleDto article,
                                              @RequestParam Long lessonId) {
        Long id = service.create(detailsUser.getUser().getId(), mapper.map(article), lessonId);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteArticleById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                  @PathVariable Long id) {
        Long deletedId = service.deleteById(detailsUser.getUser().getId(), id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updateArticleById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                  @PathVariable Long id,
                                                  @Valid @RequestBody ArticleDto article) {
        Long updatedId = service.update(detailsUser.getUser().getId(), id, mapper.map(article));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
