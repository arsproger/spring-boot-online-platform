package com.it.academy.controllers;

import com.it.academy.dao.SubscriptionDao;
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
    private final SubscriptionDao subscriptionDao;

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

    @GetMapping
    public ResponseEntity<List<SubscriptionDto>> getActiveSubscriptionsByUserId(Long userId) {
        List<SubscriptionDto> subscriptions = mapper.map(subscriptionDao.getActiveSubscriptionsByUserId(userId));
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createSubscription(@RequestParam Long userId,
                                                   @RequestParam Long courseId) {
        Long id = service.save(userId, courseId);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
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

}
