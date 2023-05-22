package com.it.academy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sections")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 155)
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @NotNull
    private Course course;

    @OneToMany(mappedBy = "section")
    private List<Lesson> lessons;

}
