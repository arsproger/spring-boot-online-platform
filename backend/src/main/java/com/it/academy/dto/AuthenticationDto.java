package com.it.academy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AuthenticationDto {
    @Email(message = "Не корректный email!")
    private String username;

    @NotEmpty(message = "Пароль не может быть пустым!")
    private String password;
}
