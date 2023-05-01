package com.example.springbootonlineplatform.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "carts")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

}
