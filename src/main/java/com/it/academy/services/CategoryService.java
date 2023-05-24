package com.it.academy.services;

import com.it.academy.models.Category;

import java.util.List;

public interface CategoryService {
    Category getById(Long id);

    List<Category> getAll();

    Long save(Category category);

    Long deleteById(Long id);

    Long update(Long id, Category category);
}
