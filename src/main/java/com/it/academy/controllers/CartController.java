package com.it.academy.controllers;

import com.it.academy.dto.CourseDto;
import com.it.academy.mappers.CourseMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
@Tag(name = "Контроллер корзины пользователя")
public class CartController {
    private final CourseMapper mapper;
    private final CartService cartService;

    @GetMapping
    @Operation(summary = "Получение корзины текущего пользователя")
    public ResponseEntity<List<CourseDto>> getCoursesByUserCart(@AuthenticationPrincipal DetailsUser detailsUser) {
        List<CourseDto> courses = mapper.map(cartService.getCoursesByUserCart(detailsUser.getUser().getId()));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping("/add/{courseId}")
    public HttpStatus addCourseToCart(@AuthenticationPrincipal DetailsUser detailsUser, @PathVariable Long courseId) {
        cartService.addCourseToCart(detailsUser.getUser().getId(), courseId);
        return HttpStatus.OK;
    }

    @DeleteMapping("/delete/{courseId}")
    public HttpStatus removeCourseFromCart(@AuthenticationPrincipal DetailsUser detailsUser, @PathVariable Long courseId) {
        cartService.removeCourseFromCart(detailsUser.getUser().getId(), courseId);
        return HttpStatus.OK;
    }

}
