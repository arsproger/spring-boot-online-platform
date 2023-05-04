package com.it.academy.controllers;

import com.it.academy.dtos.CommentDto;
import com.it.academy.mappers.CommentMapper;
import com.it.academy.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService service;
    private final CommentMapper mapper;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllArticles() {
        List<CommentDto> comments = mapper.map(service.getAll());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getArticleById(@PathVariable Long id) {
        CommentDto dto = mapper.map(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createArticle(
            @RequestParam Long userId,
            @RequestParam Long lessonId,
            @RequestBody CommentDto dto) {
        Long id = service.save(userId, lessonId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteArticleById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateArticleById(@PathVariable Long id, @RequestBody CommentDto dto) {
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
