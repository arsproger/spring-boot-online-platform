package com.it.academy.services;

import com.it.academy.entities.Category;

import java.util.List;

public interface CategoryService {
    Category getById(Long id);

    List<Category> getAll();

    Long save(Category category);

    Long create(Category category);

    List<Category> getCategoriesByTitle(String title);

    Long deleteById(Long id);

    Long update(Long id, Category category);
}
