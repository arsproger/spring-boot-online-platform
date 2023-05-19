package com.it.academy.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Setter
@Getter
public class SubscriptionDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Cannot be empty")
    private LocalDate creationDate;
}
