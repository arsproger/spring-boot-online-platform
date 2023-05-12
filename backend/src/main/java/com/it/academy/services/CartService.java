package com.it.academy.services;

import com.it.academy.models.Cart;

import java.util.List;

public interface CartService {
    Cart getById(Long id);

    List<Cart> getAll();

    Long save(Cart cart);

    Long deleteById(Long id);

    Long update(Long id, Cart cart);
}
