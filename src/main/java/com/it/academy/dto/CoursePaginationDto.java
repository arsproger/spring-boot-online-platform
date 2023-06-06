package com.it.academy.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursePaginationDto {
    private List<CourseDto> courses;
    private Integer amountPage;
}
