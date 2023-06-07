package com.it.academy.dto;

import com.it.academy.entities.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Schema(description = "Сущность для корзины пользователя")
public class CartDto {
    private Long id;
    private List<Course> courses;
}
