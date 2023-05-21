package com.it.academy.services.impl;

import com.it.academy.models.Cart;
import com.it.academy.repositories.CartRepository;
import com.it.academy.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository repo;

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
        Cart cart = getById(id);

        cart.setUser(updatedCart.getUser());
        cart.setCourses(updatedCart.getCourses());

        return repo.save(cart).getId();
    }

}
