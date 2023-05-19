package com.it.academy.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
@Setter
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 100, message = "Title must have a maximum of {max} characters")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Cannot be empty")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotEmpty(message = "Cannot be empty")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @NotEmpty(message = "Cannot be empty")
    private Lesson lesson;

}
