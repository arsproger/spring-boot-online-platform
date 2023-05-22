package com.it.academy.controllers;

import com.it.academy.dao.LessonDao;
import com.it.academy.dto.LessonDto;
import com.it.academy.mappers.LessonMapper;
import com.it.academy.services.impl.LessonServiceImpl;
import com.it.academy.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@AllArgsConstructor
public class LessonController {
    private final LessonServiceImpl service;
    private final LessonDao lessonDao;
    private final LessonMapper mapper;
    private final ObjectValidator<LessonDto> validator;

    @GetMapping
    public ResponseEntity<List<LessonDto>> getAllLessons() {
        List<LessonDto> lessons = mapper.map(service.getAll());
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable Long id) {
        LessonDto dto = mapper.map(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createLesson(@RequestBody LessonDto dto) {
        if (!validator.validate(dto).isEmpty()) return new ResponseEntity<>(validator.validate(dto), HttpStatus.BAD_REQUEST);
        Long id = service.save(mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteLessonById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLessonById(@PathVariable Long id, @RequestBody LessonDto dto) {
        if (!validator.validate(dto).isEmpty()) return new ResponseEntity<>(validator.validate(dto), HttpStatus.BAD_REQUEST);
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/filter/duration")
    public ResponseEntity<List<LessonDto>> durationFilter(
            @RequestParam Integer from,
            @RequestParam Integer to) {
        List<LessonDto> lessons = mapper.map(lessonDao.filterByDuration(from, to));
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

}
