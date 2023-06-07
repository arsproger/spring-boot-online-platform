package com.it.academy.controllers;

import com.it.academy.dto.ReviewDto;
import com.it.academy.mappers.ReviewMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Tag(name = "Контроллер отзывов к курсу")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper mapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> comments = mapper.map(reviewService.getAll());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    @Operation(summary = "Получение всех отзывов определенного автора",
            description = "Нужно передать id пользователя")
    public ResponseEntity<List<ReviewDto>> getCourseReviewsByAuthorId(@PathVariable Long authorId) {
        List<ReviewDto> reviews = mapper.map(reviewService.getCourseReviewsByAuthorId(authorId));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Получение отзывов определенного курса")
    public ResponseEntity<List<ReviewDto>> getReviewsByCourseId(@PathVariable Long courseId) {
        List<ReviewDto> reviews = mapper.map(reviewService.getReviewsByCourse(courseId));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/course/avg-grade/{courseId}")
    @Operation(summary = "Получение средней оценки курса по его id")
    public ResponseEntity<Double> getCourseAvgGrade(@PathVariable Long courseId) {
        Double avgGrade = reviewService.getCourseAvgGrade(courseId);
        return new ResponseEntity<>(avgGrade, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{courseId}")
    @Operation(summary = "Создание отзыва к курсу",
            description = "Автором отзыва будет назначен текущий пользователь")
    public ResponseEntity<Long> createReview(
            @AuthenticationPrincipal DetailsUser detailsUser,
            @PathVariable Long courseId,
            @RequestBody @Valid ReviewDto dto) {
        Long id = reviewService.save(detailsUser.getUser().getId(), courseId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Long> deleteReviewById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                 @PathVariable Long reviewId) {
        Long deletedId = reviewService.deleteById(detailsUser.getUser().getId(), reviewId);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{reviewId}")
    public ResponseEntity<Long> updateReviewById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                 @PathVariable Long reviewId,
                                                 @RequestBody @Valid ReviewDto dto) {
        Long updatedId = reviewService.update(detailsUser.getUser().getId(), reviewId, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/count")
    @Operation(summary = "Получение количества всех отзывов")
    public ResponseEntity<Integer> getCountOfAllReviews() {
        Integer reviewCount = reviewService.getCountOfAllReviews();
        return new ResponseEntity<>(reviewCount, HttpStatus.OK);
    }

}
