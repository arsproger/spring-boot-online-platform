package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Schema(description = "Сущность раздела")
public class SectionDto {
    @NotBlank(message = "Name cannot be empty!")
    @Size(max = 100, message = "Name must have a maximum of 155 characters!")
    private String name;
}
