package com.it.academy.services.impl;

import com.it.academy.dao.CartDao;
import com.it.academy.dao.CourseDao;
import com.it.academy.entities.Cart;
import com.it.academy.entities.Course;
import com.it.academy.entities.User;
import com.it.academy.repositories.CartRepository;
import com.it.academy.services.CartService;
import com.it.academy.services.CourseService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartDao cartDao;
    private final CourseDao courseDao;
    private final UserService userService;
    private final CourseService courseService;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Course> getCoursesByUserCart(Long userId) {
        return courseDao.getCoursesByUserCart(userId);
    }

    @Override
    public void addCourseToCart(Long userId, Long courseId) {
        User user = userService.getById(userId);
        Course course = courseService.getById(courseId);
        user.getCourses().add(course);
        userService.save(user);
        cartDao.addCourseToCart(userId, courseId);
    }

    @Override
    public void removeCourseFromCart(Long userId, Long courseId) {
        cartDao.removeCourseFromCart(userId, courseId);
    }

    @Override
    public Double getUserCartCoursesPriceSum(Long id) {
        Double cartCoursesSum = cartDao.getUserCartCoursesPriceSum(id);
        return cartCoursesSum != null ? cartCoursesSum : 0.0;
    }

}
