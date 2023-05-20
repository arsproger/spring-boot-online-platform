package com.it.academy.services.impl;

import com.it.academy.models.Category;
import com.it.academy.repositories.CategoryRepository;
import com.it.academy.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;

    @Override
    public Category getById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + id));
    }

    @Override
    public List<Category> getAll() {
        return repo.findAll();
    }

    @Override
    public Long save(Category category) {
        return repo.save(category).getId();
    }

    @Override
    public Long deleteById(Long id) {
        repo.deleteById(id);
        return id;
    }

    @Override
    public Long update(Long id, Category updatedCategory) {
        Category category = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + id));

        category.setTitle(updatedCategory.getTitle());

        return repo.save(category).getId();
    }
}
