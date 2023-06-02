package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Setter
@Getter
@Schema(description = "Сущность подписки")
public class SubscriptionDto {
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Cannot be empty")
    private LocalDate creationDate;
}
