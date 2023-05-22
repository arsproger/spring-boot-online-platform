package com.it.academy.controllers;

import com.it.academy.dto.ReviewDto;
import com.it.academy.mappers.ReviewMapper;
import com.it.academy.services.ReviewService;
import com.it.academy.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService service;
    private final ReviewMapper mapper;
    private final ObjectValidator<ReviewDto> validator;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> comments = mapper.map(service.getAll());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        ReviewDto dto = mapper.map(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createReview(
            @RequestParam Long userId,
            @RequestParam Long courseId,
            @RequestBody ReviewDto dto) {
        if (!validator.validate(dto).isEmpty()) return new ResponseEntity<>(validator.validate(dto), HttpStatus.BAD_REQUEST);
        Long id = service.save(userId, courseId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteReviewById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReviewById(@PathVariable Long id, @RequestBody ReviewDto dto) {
        if (!validator.validate(dto).isEmpty()) return new ResponseEntity<>(validator.validate(dto), HttpStatus.BAD_REQUEST);
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
