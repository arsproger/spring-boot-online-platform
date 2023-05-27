package com.it.academy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lessons")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 155)
    private String title;

    @NotEmpty
    private String description;

    private Double duration;

    private String videoUrl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    @OneToMany(mappedBy = "lesson")
    private List<Article> articles;

    @OneToMany(mappedBy = "lesson")
    private List<Comment> comments;

}
