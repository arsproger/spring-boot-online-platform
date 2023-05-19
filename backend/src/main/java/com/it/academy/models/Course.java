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

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name must have a maximum of {max} characters")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Price cannot be empty")
    @DecimalMin(value = "0.00", message = "Price must be greater than or equal to {value}")
    @DecimalMax(value = "9999.99", message = "Price must be less than or equal to {value}")
    private BigDecimal price;

    private String language;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @NotBlank(message = "Category cannot be empty")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @NotEmpty(message = "Cannot be empty")
    private User author;

    @OneToMany(mappedBy = "course")
    private List<Section> sections;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews;

    @OneToMany(mappedBy = "course")
    private List<Subscription> subscriptions;

    @ManyToMany(mappedBy = "courses")
    private List<Cart> carts;

}
