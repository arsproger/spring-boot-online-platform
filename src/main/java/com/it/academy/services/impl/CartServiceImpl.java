package com.it.academy.services.impl;

import com.it.academy.dao.CartDao;
import com.it.academy.dao.CourseDao;
import com.it.academy.entities.Course;
import com.it.academy.services.CartService;
import com.it.academy.services.CourseService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartDao cartDao;
    private final CourseDao courseDao;
    private final UserService userService;
    private final CourseService courseService;

    @Override
    public List<Course> getCoursesByUserCart(Long userId) {
        userService.getById(userId);
        return courseDao.getCoursesByUserCart(userId);
    }

    @Override
    public void addCourseToCart(Long userId, Long courseId) {
        userService.getById(userId);
        courseService.getById(courseId);
        cartDao.addCourseToCart(userId, courseId);
    }

    @Override
    public void removeCourseFromCart(Long userId, Long courseId) {
        userService.getById(userId);
        courseService.getById(courseId);
        cartDao.removeCourseFromCart(userId, courseId);
    }

}
