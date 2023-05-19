package com.it.academy.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Setter
@Getter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Cannot be empty")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotEmpty(message = "Cannot be empty")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @NotEmpty(message = "Cannot be empty")
    private Course course;

}
