package com.it.academy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.it.academy.controllers.S3Controller;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Setter
@Getter
@Schema(description = "Сущность урока")
public class LessonDto {
    private Long id;

    @NotEmpty(message = "Title cannot be empty!")
    @Size(max = 155, message = "Title must have a maximum of 155 characters")
    private String title;

    @NotEmpty(message = "Description cannot be empty!")
    private String description;

    private Double duration;

    @JsonProperty("videoName")
    private String videoUrl;

    @JsonProperty("videoUrl")
    public String getPhotoUrl() {
        return linkTo(methodOn(S3Controller.class).download(videoUrl)).withRel("video").getHref();
    }

}
