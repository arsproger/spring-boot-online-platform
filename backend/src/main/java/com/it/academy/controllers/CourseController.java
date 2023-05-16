package com.it.academy.controllers;

import com.it.academy.dao.CourseDao;
import com.it.academy.dto.CourseDto;
import com.it.academy.mappers.CourseMapper;
import com.it.academy.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService service;
    private final CourseDao courseDao;
    private final CourseMapper mapper;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = mapper.map(service.getAll());
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        CourseDto course = mapper.map(service.getById(id));
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createCourse(@RequestParam Long authorId,
                                             @RequestParam Long categoryId,
                                             @RequestBody CourseDto course) {
        Long id = service.save(authorId, categoryId, mapper.map(course));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCourseById(@PathVariable Long id) {
        Long deletedId = service.deleteById(id);
        return new ResponseEntity<>(deletedId, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCourseById(@PathVariable Long id, @RequestBody CourseDto course) {
        Long updatedId = service.update(id, mapper.map(course));
        return new ResponseEntity<>(updatedId, HttpStatus.OK);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<CourseDto>> getByAuthorId(@PathVariable("id") Long authorId) {
        List<CourseDto> courses = mapper.map(courseDao.getByAuthorId(authorId));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/filter/price")
    public ResponseEntity<List<CourseDto>> priceFilter(
            @RequestParam(defaultValue = "ask") String filter) {
        List<CourseDto> courses = filter.equals("desc")
                ? mapper.map(courseDao.filterByPriceDesc())
                : mapper.map(courseDao.filterByPriceAsk());

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/language")
    public ResponseEntity<List<CourseDto>> getByLanguage(@RequestParam String language) {
        List<CourseDto> courses = mapper.map(courseDao.getByLanguage(language));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<CourseDto>> getCourseByCategoryId(@PathVariable("id") Long categoryId) {
        List<CourseDto> courses = mapper.map(courseDao.getCourseByCategoryId(categoryId));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

}
