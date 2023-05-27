package com.it.academy.controllers;

import com.it.academy.dto.CategoryDto;
import com.it.academy.mappers.CategoryMapper;
import com.it.academy.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@Tag(name = "Контроллер для категорий к курсу")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> comments = categoryMapper.map(categoryService.getAll());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto dto = categoryMapper.map(categoryService.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createCategory(@RequestBody @Valid CategoryDto dto) {
        Long id = categoryService.create(categoryMapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCategoryById(@PathVariable Long id) {
        Long deletedId = categoryService.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCategoryById(@PathVariable Long id, @Valid @RequestBody CategoryDto dto) {
        Long updatedId = categoryService.update(id, categoryMapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByTitle(@PathVariable String title) {
        List<CategoryDto> comments = categoryMapper.map(categoryService.getCategoriesByTitle(title));
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
