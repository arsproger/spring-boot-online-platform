package com.it.academy.controllers;

import com.it.academy.dto.CommentDto;
import com.it.academy.mappers.CommentMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> comments = mapper.map(service.getAll());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id) {
        CommentDto dto = mapper.map(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Создание комментария к уроку",
            description = "Автором комментария будет назначен текущий пользователь")
    public ResponseEntity<Long> createComment(
            @AuthenticationPrincipal DetailsUser detailsUser,
            @RequestParam Long lessonId,
            @RequestBody CommentDto dto) {
        Long id = service.create(detailsUser.getUser().getId(), lessonId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCommentById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCommentById(@PathVariable Long id, @RequestBody CommentDto dto) {
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }


}
