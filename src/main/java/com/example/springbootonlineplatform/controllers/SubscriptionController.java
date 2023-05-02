package com.example.springbootonlineplatform.controllers;

import com.example.springbootonlineplatform.dtos.CartDto;
import com.example.springbootonlineplatform.dtos.SubscriptionDto;
import com.example.springbootonlineplatform.mappers.SubscriptionMapper;
import com.example.springbootonlineplatform.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
@AllArgsConstructor
public class SubscriptionController {
    private final SubscriptionService service;
    private final SubscriptionMapper mapper;

    @GetMapping
    public ResponseEntity<List<SubscriptionDto>> getAllArticles() {
        List<SubscriptionDto> dtos = mapper.map(service.getAll());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto> getArticleById(@PathVariable Long id) {
        SubscriptionDto dto = mapper.map(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createArticle(@RequestBody SubscriptionDto dto) {
        Long id = service.save(mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteArticleById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateArticleById(@PathVariable Long id, @RequestBody SubscriptionDto dto) {
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }
}
