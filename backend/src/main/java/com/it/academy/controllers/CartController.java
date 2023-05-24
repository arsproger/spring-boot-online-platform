package com.it.academy.controllers;

import com.it.academy.dao.CourseDao;
import com.it.academy.dto.CourseDto;
import com.it.academy.mappers.CourseMapper;
import com.it.academy.security.DetailsUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
@Tag(name = "Контроллер корзины пользователя")
public class CartController {
    private final CourseMapper mapper;
    private final CourseDao courseDao;

    @GetMapping
    @Operation(summary = "Получение корзины текущего пользователя")
    public ResponseEntity<List<CourseDto>> getCoursesByUserCart(@AuthenticationPrincipal DetailsUser detailsUser) {
        List<CourseDto> courses = mapper.map(courseDao.getCoursesByUserCart(detailsUser.getUser().getId()));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

}
