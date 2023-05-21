package com.it.academy.controllers;

import com.it.academy.dao.SectionDao;
import com.it.academy.dto.SectionDto;
import com.it.academy.mappers.SectionMapper;
import com.it.academy.services.SectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section")
@AllArgsConstructor
@Tag(name = "Контроллер разделов курса")
public class SectionController {
    private final SectionService service;
    private final SectionDao sectionDao;
    private final SectionMapper mapper;

    @GetMapping
    public ResponseEntity<List<SectionDto>> getAllSections() {
        List<SectionDto> comments = mapper.map(service.getAll());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable Long id) {
        SectionDto dto = mapper.map(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createSection(
            @RequestParam Long courseId,
            @RequestBody SectionDto dto) {
        Long id = service.save(courseId, mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteSectionById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSectionById(@PathVariable Long id, @RequestBody SectionDto dto) {
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    @Operation(summary = "Получение всех разделов определенного курса")
    public ResponseEntity<List<SectionDto>> getSectionByCourseId(@PathVariable("id") Long courseId) {
        List<SectionDto> sections = mapper.map(sectionDao.getSectionByCourseId(courseId));
        return new ResponseEntity<>(sections, HttpStatus.OK);
    }

}
