package com.it.academy.controllers;

import com.it.academy.dao.CourseDao;
import com.it.academy.dto.CourseDto;
import com.it.academy.mappers.CourseMapper;
import com.it.academy.services.CourseService;
import com.it.academy.services.PaymentService;
import com.stripe.exception.StripeException;
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
    private final PaymentService paymentService;

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

    /*@PostMapping("/create/{authorId}")
    public ResponseEntity<Long> createCourse(@RequestBody CourseDto course, @PathVariable Long authorId) {
        Long id = service.create(mapper.map(course), authorId);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }*/

    @PostMapping("/create/{authorId}")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto course, @PathVariable Long authorId) throws StripeException {
        Long id = service.create((mapper.map(course)), authorId);
        if (id == null) {
            String link = paymentService.generateOnboardingLink(paymentService.createStripeAccount(authorId));
            /*HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(link));
            return new ResponseEntity<>(headers, HttpStatus.FOUND);*/
            return new ResponseEntity<>(link, HttpStatus.FOUND);
        }
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


}
