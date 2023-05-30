package com.it.academy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.it.academy.controllers.S3Controller;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Setter
@Getter
@Schema(description = "Сущность курса")
public class CourseDto {
    private Long id;

    @NotEmpty(message = "Name cannot be empty!")
    @Size(max = 50, message = "Description must have a maximum of 155 characters!")
    private String name;

    @NotEmpty(message = "Description cannot be empty!")
    private String description;

    private LocalDate created;

    @NotNull(message = "Price cannot be empty!")
    private BigDecimal price;

    @NotEmpty(message = "Language cannot be empty!")
    @Size(max = 30, message = "Language must have a maximum of 155 characters!")
    private String language;

    @JsonProperty("imageName")
    private String imageUrl;

    @JsonProperty("imageUrl")
    public String getPhotoUrl() {
        return linkTo(methodOn(S3Controller.class).download(imageUrl)).withRel("image").getHref();
    }
}
