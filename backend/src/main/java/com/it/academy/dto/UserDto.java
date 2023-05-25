package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Schema(description = "Сущность пользователя")
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
}
