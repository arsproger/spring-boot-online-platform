package com.example.springbootonlineplatform.services;

import com.example.springbootonlineplatform.models.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartService {
    Cart getById(Long id);
    List<Cart> getAll();
    Long save(Cart cart);
    void deleteById(Long id);
    Long update(Long id, Cart cart);
}
