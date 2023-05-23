package com.it.academy.validation;

import com.it.academy.dto.CourseDto;
import com.it.academy.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CourseDtoValidator {
    private final CourseRepository courseRepository;

    public List<String> validate(CourseDto courseDto) {
        List<String> errors = new ArrayList<>();

        if (courseDto.getName() == null || courseDto.getName().isEmpty()) {
            errors.add("Name cannot be empty!");
        } else if (courseDto.getName().length() > 155) {
            errors.add("Name must have a maximum of 155 characters!");
        } else if (!courseRepository.getCourseByName(courseDto.getName()).isEmpty()) {
            errors.add("Course with this name already exists!");
        }

        if (courseDto.getDescription() == null || courseDto.getDescription().isEmpty()) {
            errors.add("Description cannot be empty!");
        }

        if (courseDto.getPrice() == null) {
            errors.add("Price cannot be null!");
        } else if (courseDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Price must be greater than 0.00!");
        } else if (courseDto.getPrice().compareTo(new BigDecimal("9999.99")) >= 0) {
            errors.add("Price must be less than 9999.99!");
        }

        if (courseDto.getCategory() == null) {
            errors.add("Category cannot be null!");
        }

        return errors;
    }
}
