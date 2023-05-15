package com.it.academy.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lessons")
@Setter
@Getter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double duration;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    @OneToMany(mappedBy = "lesson")
    private List<Article> articles;

    @OneToMany(mappedBy = "lesson")
    private List<Comment> comments;

    @OneToOne(mappedBy = "lesson")
    private Video video;

}
