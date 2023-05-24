package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@Schema(description = "Сущность раздела")
public class SectionDto {
    @NotBlank(message = "Name cannot be empty!")
    @Size(max = 100, message = "Name must have a maximum of 155 characters!")
    private String name;
}
