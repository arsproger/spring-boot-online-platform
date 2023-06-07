package com.it.academy.services.impl;

import com.it.academy.dao.CartDao;
import com.it.academy.dao.CourseDao;
import com.it.academy.entities.Cart;
import com.it.academy.entities.Course;
import com.it.academy.entities.User;
import com.it.academy.repositories.CartRepository;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.repositories.UserRepository;
import com.it.academy.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartDao cartDao;
    private final CourseDao courseDao;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

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
        User user = userRepository.findById(userId).get();
        Course course = courseRepository.findById(courseId).get();
        user.getCourses().add(course);
        userRepository.save(user);
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
