package com.it.academy.controllers;

import com.it.academy.dao.CourseDao;
import com.it.academy.dto.CourseDto;
import com.it.academy.mappers.CourseMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
@Tag(name = "Контроллер для курса")
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
    @Operation(summary = "Создание курса",
            description = "Автором курса будет назначен текущий пользователь")
    public ResponseEntity<Long> createCourse(@AuthenticationPrincipal DetailsUser detailsUser,
                                             @RequestParam Long categoryId,
                                             @RequestBody CourseDto course) {
        Long id = service.save(detailsUser.getUser().getId(), categoryId, mapper.map(course));
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
    @Operation(summary = "Получение всех курсов по id автора")
    public ResponseEntity<List<CourseDto>> getByAuthorId(@PathVariable("id") Long authorId) {
        List<CourseDto> courses = mapper.map(courseDao.getByAuthorId(authorId));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/filter/price")
    @Operation(summary = "Фильтрация курса по цене",
            description = "По умолчанию фильтрацию будет по возрастанию, " +
                    "для фильтрации по убыванию нужно передать параметр filter как desc")
    public ResponseEntity<List<CourseDto>> priceFilter(
            @RequestParam(defaultValue = "ask")
            @Parameter(description = "Тип фильтрации по возрастанию и по убыванию") String filter) {
        List<CourseDto> courses = filter.equals("desc")
                ? mapper.map(courseDao.filterByPriceDesc())
                : mapper.map(courseDao.filterByPriceAsk());

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/language")
    @Operation(summary = "Получение всех курсов по определенному языку")
    public ResponseEntity<List<CourseDto>> getByLanguage(@RequestParam String language) {
        List<CourseDto> courses = mapper.map(courseDao.getByLanguage(language));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    @Operation(summary = "Получение всех курсов по определенной категории")
    public ResponseEntity<List<CourseDto>> getCourseByCategoryId(@PathVariable("id") Long categoryId) {
        List<CourseDto> courses = mapper.map(courseDao.getCourseByCategoryId(categoryId));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    @Operation(summary = "Получение курсов по имени")
    public ResponseEntity<List<CourseDto>> getCourseByName(@PathVariable String name) {
//        List<CourseDto> courses = mapper.map(courseDao.getCourseByName(name));
        return new ResponseEntity<>(courseDao.getCourseByName(name), HttpStatus.OK);
    }

    @GetMapping("/duration/{id}")
    @Operation(summary = "Получение длительности курса по его id")
    public ResponseEntity<Double> getCourseDurationSum(@PathVariable("id") Long courseId) {
        Double durationSun = courseDao.getCourseDurationSum(courseId);
        return new ResponseEntity<>(durationSun, HttpStatus.OK);
    }

}
