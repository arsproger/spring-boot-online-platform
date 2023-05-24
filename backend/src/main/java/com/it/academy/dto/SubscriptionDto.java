package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Setter
@Getter
@Schema(description = "Сущность подписки")
public class SubscriptionDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Cannot be empty")
    private LocalDate creationDate;
}
