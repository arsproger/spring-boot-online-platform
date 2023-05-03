package com.it.academy.dtos;

import com.example.springbootonlineplatform.models.*;
import com.it.academy.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
