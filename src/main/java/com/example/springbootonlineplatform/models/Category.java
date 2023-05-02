package com.example.springbootonlineplatform.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;
}
