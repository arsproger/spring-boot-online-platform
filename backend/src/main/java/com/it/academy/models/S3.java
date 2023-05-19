package com.it.academy.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "s3")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class S3 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double size;
    private String url;
    private LocalDate createDate;

    @OneToOne(mappedBy = "s3")
    private Lesson lesson;

}
