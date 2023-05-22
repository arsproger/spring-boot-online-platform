package com.it.academy.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
public class UserDto {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name must have a maximum of {max} characters")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    @Size(max = 100, message = "Surname must have a maximum of {max} characters")
    private String surname;

    private LocalDate dateOfBirth;

    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
