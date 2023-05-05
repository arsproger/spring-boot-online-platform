package com.it.academy.controllers;

import com.it.academy.dtos.CartDto;
import com.it.academy.dtos.CourseDto;
import com.it.academy.mappers.CartMapper;
import com.it.academy.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CartService service;
    private final CartMapper mapper;

    @GetMapping("/user-cart/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable Long userId) {
        CartDto cart = mapper.map(service.getByUserId(userId));
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
