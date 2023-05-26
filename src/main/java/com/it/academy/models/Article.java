package com.it.academy.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "articles")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;

    @OneToMany
    private List<Comment> comments;
}