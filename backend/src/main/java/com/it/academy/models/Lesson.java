package com.it.academy.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lessons")
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "course_id", referencedColumnName = "id")
//    private Course course;
    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    @OneToMany(mappedBy = "lesson")
    private List<Article> articles;

    @OneToMany
    private List<Comment> comments;
}
