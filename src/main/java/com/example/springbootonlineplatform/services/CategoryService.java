package com.example.springbootonlineplatform.services;

import com.example.springbootonlineplatform.models.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    Category getById(Long id);
    List<Category> getAll();
    Long save(Category category);
    void deleteById(Long id);
    Long update(Long id, Category category);
}
