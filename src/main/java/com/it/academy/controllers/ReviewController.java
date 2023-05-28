package com.it.academy.controllers;

import com.it.academy.dto.ReviewDto;
import com.it.academy.mappers.ReviewMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
@Tag(name = "Контроллер отзывов к курсу")
public class ReviewController {
    private final ReviewService service;
    private final ReviewMapper mapper;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> comments = mapper.map(service.getAll());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    @Operation(summary = "Получение всех отзывов определенного автора",
            description = "Нужно передать id пользователя")
    public ResponseEntity<List<ReviewDto>> getCourseReviewsByAuthorId(@PathVariable Long authorId) {
        List<ReviewDto> reviews = mapper.map(service.getCourseReviewsByAuthorId(authorId));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Получение отзывов определенного курса")
    public ResponseEntity<List<ReviewDto>> getReviewsByCourseId(@PathVariable Long courseId) {
        List<ReviewDto> reviews = mapper.map(service.getReviewsByCourse(courseId));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/course/avg-grade/{courseId}")
    @Operation(summary = "Получение средней оценки курса по его id")
    public ResponseEntity<Double> getCourseAvgGrade(@PathVariable Long courseId) {
        Double avgGrade = service.getCourseAvgGrade(courseId);
        return new ResponseEntity<>(avgGrade, HttpStatus.OK);
    }

    @PostMapping("/{courseId}")
    @Operation(summary = "Создание отзыва к курсу",
            description = "Автором отзыва будет назначен текущий пользователь")
    public ResponseEntity<Long> createReview(
            @AuthenticationPrincipal DetailsUser detailsUser,
            @PathVariable Long courseId,
            @RequestBody @Valid ReviewDto dto) {
        Long id = service.save(detailsUser.getUser().getId(), courseId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Long> deleteReviewById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                 @PathVariable Long reviewId) {
        Long deletedId = service.deleteById(detailsUser.getUser().getId(), reviewId);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Long> updateReviewById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                 @PathVariable Long reviewId,
                                                 @RequestBody @Valid ReviewDto dto) {
        Long updatedId = service.update(detailsUser.getUser().getId(), reviewId, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
