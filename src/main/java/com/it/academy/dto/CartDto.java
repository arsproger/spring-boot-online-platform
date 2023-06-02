package com.it.academy.dto;

import com.it.academy.entities.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartDto {
    private Long id;

    private List<Course> courses;
}
