package com.example.springbootonlineplatform.dtos;

import com.example.springbootonlineplatform.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private String title;
    private List<Long> coursesId;
}
