package com.it.academy.services.impl;

import com.it.academy.dao.CategoryDao;
import com.it.academy.exceptions.AppException;
import com.it.academy.models.Category;
import com.it.academy.models.Course;
import com.it.academy.repositories.CategoryRepository;
import com.it.academy.repositories.CourseRepository;
import com.it.academy.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDao categoryDao;
    private final CourseRepository courseRepository;

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new AppException("Category not found with id: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Long save(Category category) {
        return categoryRepository.save(category).getId();
    }

    @Override
    public Long create(Category category) {
        Category createdCategory = new Category();
        createdCategory.setTitle(category.getTitle());
        return categoryRepository.save(category).getId();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        List<Course> courses = category.getCourses();
        courses.forEach(course -> course.setCategory(null));
        courseRepository.saveAll(courses);

        categoryRepository.delete(category);
        return id;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long update(Long id, Category updatedCategory) {
        Category category = getById(id);
        category.setTitle(updatedCategory.getTitle());
        return categoryRepository.save(category).getId();
    }

    @Override
    public List<Category> getCategoriesByTitle(String title) {
        return categoryDao.getCategoriesByTitle(title);
    }
}
