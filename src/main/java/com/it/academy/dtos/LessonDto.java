package com.it.academy.dtos;

import lombok.Data;

@Data
public class LessonDto {
    private String title;
    private String description;
    private Long courseId;
   /* private List<Article> articles;
    private List<Comment> comments;*/
}
