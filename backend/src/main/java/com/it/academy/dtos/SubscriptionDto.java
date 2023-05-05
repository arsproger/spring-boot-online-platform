package com.it.academy.dtos;

import com.it.academy.models.Course;
import com.it.academy.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionDto {
    private Long userId;
    private Long courseId;
    private LocalDate dateStart;
    private LocalDate dateFinish;
}
