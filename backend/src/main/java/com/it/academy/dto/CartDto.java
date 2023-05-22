package com.it.academy.dto;

import com.it.academy.models.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartDto {
    private List<Course> courses;
}
