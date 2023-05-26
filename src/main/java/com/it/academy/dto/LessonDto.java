package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Сущность урока")
public class LessonDto {
    @NotBlank(message = "Title cannot be empty!")
    @Size(max = 155, message = "Title must have a maximum of 155 characters")
    private String title;

    @NotBlank(message = "Description cannot be empty!")
    private String description;

    private Double duration;
}
