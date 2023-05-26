package com.it.academy.services.impl;

import com.it.academy.exceptions.AppException;
import com.it.academy.models.Cart;
import com.it.academy.repositories.CartRepository;
import com.it.academy.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository repo;

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

}
