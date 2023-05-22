package com.it.academy.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "articles")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 155)
    private String title;

    @NotNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @NotNull
    private Lesson lesson;
}