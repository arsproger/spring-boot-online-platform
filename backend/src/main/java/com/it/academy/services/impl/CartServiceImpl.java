package com.it.academy.services.impl;

import com.it.academy.models.Cart;
import com.it.academy.repositories.CartRepository;
import com.it.academy.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public Cart getById(Long id) {
        return cartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cart not found with id: " + id));
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Long save(Cart cart) {
        return cartRepository.save(cart).getId();
    }

    @Override
    public Long deleteById(Long id) {
        cartRepository.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Cart updatedCart) {
        Cart cart = cartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cart not found with id: " + id));

        cart.setUser(updatedCart.getUser());
        cart.setCourses(updatedCart.getCourses());

        return cartRepository.save(cart).getId();
    }

}
