package com.it.academy.controllers;

import com.it.academy.dto.SubscriptionDto;
import com.it.academy.mappers.SubscriptionMapper;
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

    @GetMapping
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        List<SubscriptionDto> subscriptions = mapper.map(service.getAll());
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable Long id) {
        SubscriptionDto dto = mapper.map(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create/{userId}/{courseId}")
    public ResponseEntity<SubscriptionDto> createSubscription(@PathVariable Long userId, @PathVariable Long courseId) {
        SubscriptionDto dto = mapper.map(service.create(userId, courseId));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteSubscriptionById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSubscriptionById(@PathVariable Long id, @RequestBody SubscriptionDto dto) {
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SubscriptionDto>> getSubscriptionByUserId(@PathVariable Long userId) {
        List<SubscriptionDto> subscriptions = mapper.map(service.getSubscriptionsByUserId(userId));
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);

    }

}
