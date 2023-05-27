package com.it.academy.controllers;

import com.it.academy.dao.LessonDao;
import com.it.academy.dto.LessonDto;
import com.it.academy.mappers.LessonMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.impl.LessonServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@AllArgsConstructor
@Tag(name = "Контроллер для уроков")
public class LessonController {
    private final LessonServiceImpl lessonService;
    private final LessonDao lessonDao;
    private final LessonMapper mapper;

    @GetMapping("section/{sectionId}")
    public ResponseEntity<List<LessonDto>> getLessonsBySection(@PathVariable Long sectionId) {
        List<LessonDto> lessons = mapper.map(lessonService.getLessonsBySection(sectionId));
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable Long id) {
        LessonDto dto = mapper.map(lessonService.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/{sectionId}")
    public ResponseEntity<Long> createLesson(@PathVariable Long sectionId, @RequestBody @Valid LessonDto dto) {
        Long id = lessonService.save(sectionId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteLessonById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                 @PathVariable Long id) {
        Long deletedId = lessonService.deleteById(detailsUser.getUser().getId(), id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateLessonById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                 @PathVariable Long id,
                                                 @RequestBody @Valid LessonDto dto) {
        Long updatedId = lessonService.update(detailsUser.getUser().getId(), id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
