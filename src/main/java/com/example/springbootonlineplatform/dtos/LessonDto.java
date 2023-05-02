package com.example.springbootonlineplatform.dtos;

import com.example.springbootonlineplatform.models.Article;
import com.example.springbootonlineplatform.models.Comment;
import com.example.springbootonlineplatform.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class LessonDto {
    private String title;
    private String description;
    private Long courseId;
   /* private List<Article> articles;
    private List<Comment> comments;*/
}
