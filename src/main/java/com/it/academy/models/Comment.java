package com.it.academy.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
@Setter
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 155)
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;

}