package com.it.academy.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "lessons")
@Setter
@Getter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 155)
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Double duration;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @NotNull
    private Section section;

    @OneToMany(mappedBy = "lesson")
    private List<Article> articles;

    @OneToMany(mappedBy = "lesson")
    private List<Comment> comments;
}
