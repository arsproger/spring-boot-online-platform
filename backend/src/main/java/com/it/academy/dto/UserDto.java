package com.it.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Сущность пользователя")
public class UserDto {
    private String fullName;
    private String email;
    private String password;
}
