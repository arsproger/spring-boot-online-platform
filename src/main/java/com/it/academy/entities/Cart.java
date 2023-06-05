package com.it.academy.entities;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cart")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "carts_courses",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Course> courses;
}
