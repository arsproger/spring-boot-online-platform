package com.it.academy.models;

import lombok.*;

import jakarta.persistence.*;
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

    private double size;
    private String url;
    private LocalDate createDate;

    @OneToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;

}
