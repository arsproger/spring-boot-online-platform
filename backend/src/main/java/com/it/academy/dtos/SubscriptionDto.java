package com.it.academy.dtos;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubscriptionDto {
    private LocalDate dateStart;
    private LocalDate dateFinish;
}
