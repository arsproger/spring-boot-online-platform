package com.it.academy.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
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