package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Schema(description = "Сущность отзыва")
public class ReviewDto {
    private String title;
    private String description;
    private Double grade;
    private LocalDate date;
}
