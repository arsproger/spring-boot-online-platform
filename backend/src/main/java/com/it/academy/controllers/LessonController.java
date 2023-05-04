package com.it.academy.controllers;

import com.it.academy.dtos.LessonDto;
import com.it.academy.mappers.LessonMapper;
import com.it.academy.services.impl.LessonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@AllArgsConstructor
public class LessonController {
    private final LessonServiceImpl service;
    private final LessonMapper mapper;

    @GetMapping
    public ResponseEntity<List<LessonDto>> getAllArticles() {
        List<LessonDto> dtos = mapper.map(service.getAll());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getArticleById(@PathVariable Long id) {
        LessonDto dto = mapper.map(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createArticle(@RequestBody LessonDto dto) {
        Long id = service.save(mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteArticleById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateArticleById(@PathVariable Long id, @RequestBody LessonDto dto) {
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }
}
