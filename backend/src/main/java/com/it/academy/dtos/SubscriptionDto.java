package com.it.academy.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionDto {
    private LocalDate dateStart;
    private LocalDate dateFinish;
}
