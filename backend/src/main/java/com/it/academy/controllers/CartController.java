package com.it.academy.controllers;

import com.it.academy.dao.CourseDao;
import com.it.academy.dto.CartDto;
import com.it.academy.dto.CourseDto;
import com.it.academy.mappers.CartMapper;
import com.it.academy.mappers.CourseMapper;
import com.it.academy.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CourseMapper courseMapper;
    private final CourseDao courseDao;
    private final CartMapper cartMapper;
    private final CourseService courseService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CourseDto>> getCoursesByUserCart(@PathVariable Long userId) {
        List<CourseDto> courses = courseMapper.map(courseDao.getCoursesByUserCart(userId));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping("/add/{userId/{courseId}")
    public ResponseEntity<CartDto> addCourseToCart(@PathVariable Long userId, @PathVariable Long courseId) {
        CartDto cartDto = cartMapper.map(courseService.addCourseToCart(userId, courseId));
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

}
