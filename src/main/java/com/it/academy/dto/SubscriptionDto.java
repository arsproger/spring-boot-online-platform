package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Schema(description = "Сущность подписки")
public class SubscriptionDto {
    private LocalDate dateStart;
    private LocalDate dateFinish;
}
