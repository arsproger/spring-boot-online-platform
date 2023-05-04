package com.it.academy.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sections")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @OneToMany(mappedBy = "section")
    private List<Lesson> lessons;

}
