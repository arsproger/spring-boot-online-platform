package com.it.academy.controllers;

import com.it.academy.dto.CategoryDto;
import com.it.academy.mappers.CategoryMapper;
import com.it.academy.services.CategoryService;
import com.it.academy.validation.CategoryDtoValidator;
import com.it.academy.validation.ObjectValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;
    private final CategoryDtoValidator validator;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> comments = mapper.map(service.getAll());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto dto = mapper.map(service.getById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto dto) {
        if (!validator.validate(dto).isEmpty()) return new ResponseEntity<>(validator.validate(dto), HttpStatus.BAD_REQUEST);
        Long id = service.save(mapper.map(dto));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCategoryById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable Long id, @RequestBody CategoryDto dto) {
        if (!validator.validate(dto).isEmpty()) return new ResponseEntity<>(validator.validate(dto), HttpStatus.BAD_REQUEST);
        Long updatedId = service.update(id, mapper.map(dto));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

}
