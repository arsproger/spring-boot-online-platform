package com.it.academy.services.impl;

import com.it.academy.exceptions.AppException;
import com.it.academy.models.Cart;
import com.it.academy.models.Course;
import com.it.academy.models.User;
import com.it.academy.repositories.CartRepository;
import com.it.academy.services.CartService;
import com.it.academy.services.CourseService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository repo;
    private final UserService userService;
    private final CourseService courseService;

    @Override
    public Cart getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new AppException("Cart not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Cart> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Cart cart) {
        return repo.save(cart).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Cart updatedCart) {
        Cart cart = getById(id);

        cart.setUser(updatedCart.getUser());
        cart.setCourses(updatedCart.getCourses());

        return repo.save(cart).getId();
    }

    @Override
    public Cart addCourseToCart(Long userId, Long courseId) {
        User user = userService.getById(userId);
        Course course = courseService.getById(courseId);
        Cart cart = user.getCart();
        cart.setCourses(Collections.singletonList(course));
        repo.save(cart);
        return user.getCart();
    }

    @Override
    public boolean removeCourseFromCart(Long userId, Long courseId) {
        User user = userService.getById(userId);
        Cart cart = user.getCart();

        boolean courseExists = cart.getCourses().stream()
                .anyMatch(course -> course.getId().equals(courseId));
        if(courseExists) {
            cart.getCourses().removeIf(course -> course.getId().equals(courseId));
            userService.save(user);
            return true;
        } else return false;
    }


}
