package com.it.academy.controllers;

import com.it.academy.dao.CourseDao;
import com.it.academy.dto.CartDto;
import com.it.academy.dto.CourseDto;
import com.it.academy.mappers.CartMapper;
import com.it.academy.mappers.CourseMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.services.CartService;
import com.it.academy.services.CourseService;
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
    private final CourseDao courseDao;
    private final CartMapper cartMapper;
    private final CartService cartService;

    @GetMapping
    @Operation(summary = "Получение корзины текущего пользователя")
    public ResponseEntity<List<CourseDto>> getCoursesByUserCart(@AuthenticationPrincipal DetailsUser detailsUser) {
        List<CourseDto> courses = mapper.map(courseDao.getCoursesByUserCart(detailsUser.getUser().getId()));
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartDto> addCourseToCart(@RequestParam Long userId, @RequestParam Long courseId) {
        CartDto cartDto = cartMapper.map(cartService.addCourseToCart(userId, courseId));
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> removeCourseFromCart(@RequestParam Long userId, @RequestParam Long courseId) {
        Boolean isDeleted = cartService.removeCourseFromCart(userId, courseId);
        if (isDeleted) return new ResponseEntity<>(isDeleted, HttpStatus.OK);
        else return new ResponseEntity<>(isDeleted, HttpStatus.BAD_REQUEST);
    }

}
