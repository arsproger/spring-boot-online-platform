package com.it.academy.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.it.academy.controllers.S3Controller;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Setter
@Getter
@Schema(description = "Сущность пользователя")
public class UserDto {
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 155, message = "Name must have a maximum of 155 characters")
    private String fullName;

    private LocalDate dateOfBirth;

    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @JsonIgnore
    private String imageUrl;

    @JsonProperty("image")
    public String getPhotoUrl() {
        return linkTo(methodOn(S3Controller.class).download(imageUrl)).withRel("image").getHref();
    }

}
