package com.it.academy.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Сущность статьи")
public class ArticleDto {
    private String title;
    private String text;
}
