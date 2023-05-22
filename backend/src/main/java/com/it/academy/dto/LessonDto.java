package com.it.academy.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class LessonDto {
    @NotBlank(message = "Title cannot be empty!")
    @Size(max = 155, message = "Title must have a maximum of 155 characters")
    private String title;

    @NotBlank(message = "Description cannot be empty!")
    private String description;

    private Double duration;
}
