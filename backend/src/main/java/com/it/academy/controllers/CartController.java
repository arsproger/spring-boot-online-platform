package com.it.academy.controllers;

import com.it.academy.dao.CourseDao;
import com.it.academy.dto.CourseDto;
import com.it.academy.mappers.CourseMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CourseMapper mapper;
    private final CourseDao courseDao;


}
