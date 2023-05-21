package com.it.academy.controllers;

import com.it.academy.dao.ReviewDao;
import com.it.academy.dto.ReviewDto;
import com.it.academy.mappers.ReviewMapper;
import com.it.academy.services.ReviewService;
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
    private final ReviewDao reviewDao;

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

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getCourseCommentsByAuthorId(Long authorId) {
        List<ReviewDto> reviews = mapper.map(reviewDao.getCourseCommentsByAuthorId(authorId));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getCommentsByCourseId(Long courseId) {
        List<ReviewDto> reviews = mapper.map(reviewDao.getCommentsByCourseId(courseId));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createReview(
            @RequestParam Long userId,
            @RequestParam Long courseId,
            @RequestBody ReviewDto dto) {
        Long id = service.save(userId, courseId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteReviewById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateReviewById(@PathVariable Long id, @RequestBody ReviewDto dto) {
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
