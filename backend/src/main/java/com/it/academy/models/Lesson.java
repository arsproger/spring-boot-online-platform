package com.it.academy.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 100, message = "Title must have a maximum of {max} characters")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Duration cannot be empty")
    private Double duration;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @NotEmpty(message = "Cannot be empty")
    private Section section;

    @OneToMany(mappedBy = "lesson")
    private List<Article> articles;

    @OneToMany(mappedBy = "lesson")
    private List<Comment> comments;
}
