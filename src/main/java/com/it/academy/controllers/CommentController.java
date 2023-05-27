package com.it.academy.controllers;

import com.it.academy.dto.CommentDto;
import com.it.academy.mappers.CommentMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.CommentService;
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
@RequestMapping("/comment")
@AllArgsConstructor
@Tag(name = "Контроллер комментариев к урокам")
public class CommentController {
    private final CommentService service;
    private final CommentMapper mapper;

    @PostMapping
    @Operation(summary = "Создание комментария к уроку",
            description = "Автором комментария будет назначен текущий пользователь")
    public ResponseEntity<Long> createComment(
            @AuthenticationPrincipal DetailsUser detailsUser,
            @RequestParam Long lessonId,
            @RequestBody @Valid CommentDto dto) {
        Long id = service.create(detailsUser.getUser().getId(), lessonId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCommentById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                  @PathVariable Long id) {
        Long deletedId = service.deleteById(detailsUser.getUser().getId(), id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Long> updateCommentById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                  @PathVariable Long commentId,
                                                  @RequestBody @Valid CommentDto dto) {
        Long updatedId = service.update(detailsUser.getUser().getId(), commentId, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<CommentDto>> getCommentsByLessonId(@PathVariable Long lessonId) {
        List<CommentDto> dtos = mapper.map(service.getCommentsByLessonId(lessonId));
        return new ResponseEntity<>(dtos,  HttpStatus.OK);
    }

}
