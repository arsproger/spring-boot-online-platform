package com.it.academy.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class VideoDto {
    private String title;
    private double size;
    private String url;
    private LocalDate created;
    private LocalDate modified;
}
