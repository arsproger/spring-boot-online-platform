package com.it.academy.dto;

import com.it.academy.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Schema(description = "Сущность курса")
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String language;
    private LocalDate created;
    private User author;
}
