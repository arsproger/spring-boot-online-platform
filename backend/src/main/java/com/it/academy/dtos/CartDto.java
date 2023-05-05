package com.it.academy.dtos;

import com.it.academy.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    //private Long userId;
    private List<Course> courses;
}
