package com.it.academy.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class AuthenticationDto {
    @Email(message = "Не корректный email!")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым!")
    private String password;
}
