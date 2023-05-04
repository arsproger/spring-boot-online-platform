package com.it.academy.services;

import com.it.academy.models.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    Category getById(Long id);
    List<Category> getAll();
    Long save(Category category);
    Long deleteById(Long id);
    Long update(Long id, Category category);
}
