package com.it.academy.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @NotEmpty
    @Size(max = 155)
    private String name;

    @NotEmpty
    private String description;

    private String imageUrl;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    @NotNull
    private BigDecimal price;

    @NotEmpty
    @Size(max = 155)
    private String language;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Section> sections;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Subscription> subscriptions;

}
