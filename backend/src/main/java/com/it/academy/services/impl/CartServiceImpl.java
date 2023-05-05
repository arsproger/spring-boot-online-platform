package com.it.academy.services.impl;

import com.it.academy.models.Cart;
import com.it.academy.models.User;
import com.it.academy.repositories.CartRepository;
import com.it.academy.services.CartService;
import com.it.academy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository repo;
    private final UserService userService;

    @Override
    public Cart getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cart not found with id: " + id));
    }

    @Override
    public List<Cart> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Cart cart) {
        return repo.save(cart).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Cart updatedCart) {
        Cart cart = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cart not found with id: " + id));

        cart.setUser(updatedCart.getUser());
        cart.setCourses(updatedCart.getCourses());
        return repo.save(cart).getId();
    }

    @Override
    public Cart getByUserId(Long userId) {
        User user = userService.getById(userId);
        return user.getCart();
    }
}
