package com.it.academy.controllers;

import com.it.academy.dto.SectionDto;
import com.it.academy.mappers.SectionMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.SectionService;
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
@RequestMapping("/section")
@RequiredArgsConstructor
@Tag(name = "Контроллер разделов курса")
public class SectionController {
    private final SectionService sectionService;
    private final SectionMapper sectionMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable Long id) {
        SectionDto dto = sectionMapper.map(sectionService.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    @Operation(summary = "Получение всех разделов определенного курса")
    public ResponseEntity<List<SectionDto>> getSectionByCourseId(@PathVariable("id") Long courseId) {
        List<SectionDto> sections = sectionMapper.map(sectionService.getSectionsByCourse(courseId));
        return new ResponseEntity<>(sections, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<Long> createSection(
            @AuthenticationPrincipal DetailsUser detailsUser,
            @RequestParam Long courseId,
            @RequestBody @Valid SectionDto dto) {
        Long id = sectionService.create(detailsUser.getUser().getId(), courseId, sectionMapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteSectionById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                  @PathVariable Long id) {
        Long deletedId = sectionService.deleteById(detailsUser.getUser().getId(), id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSectionById(@AuthenticationPrincipal DetailsUser detailsUser,
                                                  @PathVariable Long id,
                                                  @RequestBody @Valid SectionDto dto) {
        Long updatedId = sectionService.update(detailsUser.getUser().getId(), id, sectionMapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
