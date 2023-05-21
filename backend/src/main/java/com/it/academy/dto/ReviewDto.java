package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Сущность отзыва")
public class ReviewDto {
    private String title;
    private String description;
}
