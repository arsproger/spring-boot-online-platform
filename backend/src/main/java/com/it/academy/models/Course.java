package com.it.academy.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 1, max = 155)
    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Size(min = 1, max = 155)
    private String language;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NotNull
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @NotNull
    private User author;

    @OneToMany(mappedBy = "course")
    private List<Section> sections;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews;

    @OneToMany(mappedBy = "course")
    private List<Subscription> subscriptions;

}
