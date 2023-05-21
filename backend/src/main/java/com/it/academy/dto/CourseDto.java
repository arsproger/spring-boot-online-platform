package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Schema(description = "Сущность курса")
public class CourseDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String language;
}
