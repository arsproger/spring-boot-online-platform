package com.it.academy.controllers;

import com.it.academy.dao.ReviewDao;
import com.it.academy.dto.ReviewDto;
import com.it.academy.mappers.ReviewMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @GetMapping("/author/{id}")
    @Operation(summary = "Получение всех отзывов определенного пользователя",
            description = "Нужно передать id пользователя")
    public ResponseEntity<List<ReviewDto>> getCourseCommentsByAuthorId(@PathVariable("id") Long authorId) {
        List<ReviewDto> reviews = mapper.map(reviewDao.getCourseCommentsByAuthorId(authorId));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    @Operation(summary = "Получение отзывов определенного курса")
    public ResponseEntity<List<ReviewDto>> getCommentsByCourseId(@PathVariable("id") Long courseId) {
        List<ReviewDto> reviews = mapper.map(reviewDao.getCommentsByCourseId(courseId));
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/course/avg-grade/{id}")
    @Operation(summary = "Получение средней оценки курса по его id")
    public ResponseEntity<Double> getCourseAvgGrade(@PathVariable("id") Long courseId) {
        Double avgGrade = reviewDao.getCourseAvgGrade(courseId);
        return new ResponseEntity<>(avgGrade, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Создание отзыва к курсу",
            description = "Автором отзыва будет назначен текущий пользователь")
    public ResponseEntity<Long> createReview(
            @AuthenticationPrincipal DetailsUser detailsUser,
            @RequestParam Long courseId,
            @RequestBody @Valid ReviewDto dto) {
        Long id = service.save(detailsUser.getUser().getId(), courseId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteReviewById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateReviewById(@PathVariable Long id, @RequestBody @Valid ReviewDto dto) {
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
