package com.it.academy.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class ArticleDto {
    @NotBlank(message = "Title cannot be empty")
    @Size(max = 100, message = "Title must have a maximum of {max} characters")
    private String title;

    @NotBlank(message = "Text cannot be empty")
    private String text;
}
