package com.it.academy.services;

import com.it.academy.models.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartService {
    Cart getById(Long id);
    List<Cart> getAll();
    Long save(Cart cart);
    Long deleteById(Long id);
    Long update(Long id, Cart cart);
}
