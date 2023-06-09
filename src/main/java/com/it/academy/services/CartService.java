package com.it.academy.services;

import com.it.academy.entities.Cart;
import com.it.academy.entities.Course;

import java.util.List;

public interface CartService {
    List<Course> getCoursesByUserCart(Long userId);

    Cart save(Cart cart);

    void addCourseToCart(Long userId, Long courseId);

    void removeCourseFromCart(Long userId, Long courseId);

    Double getUserCartCoursesPriceSum(Long id);
}
