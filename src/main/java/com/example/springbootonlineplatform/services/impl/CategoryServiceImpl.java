package com.example.springbootonlineplatform.services.impl;

import com.example.springbootonlineplatform.models.Category;
import com.example.springbootonlineplatform.repositories.CategoryRepository;
import com.example.springbootonlineplatform.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Long update(Long id, Category updatedCategory) {
        Category category = repo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + id));

        category.setName(updatedCategory.getName());
        category.setCourses(updatedCategory.getCourses());
        return id;
    }
}
