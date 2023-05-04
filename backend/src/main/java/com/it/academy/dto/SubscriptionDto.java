package com.it.academy.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SubscriptionDto {
    private LocalDate dateStart;
    private LocalDate dateFinish;
}
