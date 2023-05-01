package com.example.springbootonlineplatform.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinish;
}
