package com.it.academy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.it.academy.controllers.S3Controller;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Setter
@Getter
@Schema(description = "Сущность отзыва")
public class ReviewDto {
    private Long id;

    @NotBlank(message = "Title cannot be empty!")
    @Size(max = 155, message = "Title must have a maximum of 155 characters!")
    private String title;

    @NotBlank(message = "Description cannot be empty!")
    private String description;
    private Double grade;
    private LocalDate date;

    private Long userFullname;
    private String userEmail;
    @JsonProperty("userImageName")
    private String userImageUrl;

    @JsonProperty("userImageUrl")
    public String getPhotoUrl() {
        return linkTo(methodOn(S3Controller.class).download(userImageUrl)).withRel("image").getHref();
    }

}
