package com.it.academy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class VideoDto {
    private String title;
    private double size;
    private String url;
    private Date created;
    private Date modified;
}
