package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@Schema(description = "Сущность категории")
public class CategoryDto {
    @NotBlank(message = "Title cannot be empty!")
    @Size(max = 155, message = "Title must have a maximum of 155 characters!")
    private String title;
}
