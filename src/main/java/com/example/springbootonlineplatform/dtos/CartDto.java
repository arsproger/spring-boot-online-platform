package com.example.springbootonlineplatform.dtos;

import com.example.springbootonlineplatform.models.Course;
import com.example.springbootonlineplatform.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private Long userId;
    private List<Long> coursesId;
}
