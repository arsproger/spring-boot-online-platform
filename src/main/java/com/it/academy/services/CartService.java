package com.it.academy.services;

import com.it.academy.models.Course;

import java.util.List;

public interface CartService {
    List<Course> getCoursesByUserCart(Long userId);

    void addCourseToCart(Long userId, Long courseId);

    void removeCourseFromCart(Long userId, Long courseId);
}
