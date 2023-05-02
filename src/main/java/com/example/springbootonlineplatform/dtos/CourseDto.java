package com.example.springbootonlineplatform.dtos;

import com.example.springbootonlineplatform.models.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
   /* private List<Lesson> lessons;
    private List<Comment> comments;
    private List<Subscription> subscriptions;*/
}
