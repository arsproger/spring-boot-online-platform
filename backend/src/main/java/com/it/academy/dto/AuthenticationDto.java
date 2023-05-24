package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Schema(description = "Сущность для авторизации")
public class AuthenticationDto {
    @Email(message = "Не корректный email!")
    private String username;

    @NotEmpty(message = "Пароль не может быть пустым!")
    private String password;
}
