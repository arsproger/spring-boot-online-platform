package com.it.academy.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CourseDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String language;
}
