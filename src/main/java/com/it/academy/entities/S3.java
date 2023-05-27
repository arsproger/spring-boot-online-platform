package com.it.academy.entities;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "s3")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class S3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private double size;

    @NotNull
    private String url;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @NotNull
    @OneToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;

    @NotNull
    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
