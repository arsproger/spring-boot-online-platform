package com.example.springbootonlineplatform.dtos;

import com.example.springbootonlineplatform.models.Article;
import com.example.springbootonlineplatform.models.Course;
import com.example.springbootonlineplatform.models.Lesson;
import com.example.springbootonlineplatform.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    private String title;
    private String description;
    private LocalDate date;
    private Long userId;
}
