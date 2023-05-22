package com.it.academy.validation;

import com.it.academy.dto.ArticleDto;
import com.it.academy.dto.CategoryDto;
import com.it.academy.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CategoryDtoValidator {
    private CategoryRepository categoryRepository;

    public List<String> validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();

        if (categoryDto.getTitle() == null || categoryDto.getTitle().isEmpty()) {
            errors.add("Title cannot be empty!");
        } else if (categoryDto.getTitle().length() > 155) {
            errors.add("Title must have a maximum of 155 characters!");
        } else if (!categoryRepository.getCategoriesByTitle(categoryDto.getTitle()).isEmpty()) {
            errors.add("Category with this title already exists!");
        }
        return errors;
    }
}