package com.it.academy.controllers;

import com.it.academy.dtos.CourseDto;
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
    public ResponseEntity<Long> createCourse(@RequestBody CourseDto course) {
        Long id = service.save(mapper.map(course));
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

    @GetMapping("/user/{userId}")
    private ResponseEntity<List<CourseDto>> getCoursesByUserId(@PathVariable Long userId) {
        List<CourseDto> courses = mapper.map(service.getAllCoursesByUserId(userId));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<CourseDto>> getCoursesByCategoryId(@PathVariable Long categoryId) {
        List<CourseDto> courses = mapper.map(service.getByCategoryId(categoryId));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/category-title/{title}")
    public ResponseEntity<List<CourseDto>> getCoursesByCategoryTitle(@PathVariable String title) {
        List<CourseDto> courses = mapper.map(service.getCoursesByCategoryTitle(title));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CourseDto>> getCoursesByName(@PathVariable String name) {
        List<CourseDto> courses = mapper.map(service.getCourseByName(name));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<CourseDto>> getCoursesByAuthorId(@PathVariable Long authorId) {
        List<CourseDto> courses = mapper.map(service.getByByAuthorId(authorId));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}/users-amount")
    public ResponseEntity<Integer> getUsersAmountByAuthor(@PathVariable Long authorId) {
        Integer amount = service.getUsersAmountByAuthor(authorId);
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }

    @GetMapping("/{courseId}/users-amount")
    public ResponseEntity<Integer> getUsersAmountByCourse(@PathVariable Long courseId) {
        Integer amount = service.getUsersAmountByCourse(courseId);
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }

}
