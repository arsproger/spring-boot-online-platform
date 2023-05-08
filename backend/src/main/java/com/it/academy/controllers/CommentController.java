package com.it.academy.controllers;

import com.it.academy.dtos.CommentDto;
import com.it.academy.mappers.CommentMapper;
import com.it.academy.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService service;
    private final CommentMapper mapper;

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<CommentDto>> getCourseCommentsByAuthorId(@PathVariable Long authorId) {
        List<CommentDto> comments = mapper.map(service.getCourseCommentsByAuthorId(authorId));
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CommentDto>> getCommentsByCourseId(@PathVariable Long courseId) {
        List<CommentDto> comments = mapper.map(service.getCommentsByCourseId(courseId));
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
