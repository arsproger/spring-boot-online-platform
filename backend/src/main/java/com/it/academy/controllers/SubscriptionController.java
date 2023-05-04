package com.it.academy.controllers;

import com.it.academy.dtos.SubscriptionDto;
import com.it.academy.dtos.UserDto;
import com.it.academy.mappers.SubscriptionMapper;
import com.it.academy.mappers.UserMapper;
import com.it.academy.models.User;
import com.it.academy.services.SubscriptionService;
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
    private final UserMapper userMapper;

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

    @GetMapping("/course/{id}")
    public ResponseEntity<List<UserDto>> getByCourseId(@PathVariable("id") Long courseId) {
        List<UserDto> users = userMapper.map(service.getUserByCourseId(courseId));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
