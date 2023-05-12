package com.it.academy.dto;

import com.it.academy.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CourseDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private String language;
}
