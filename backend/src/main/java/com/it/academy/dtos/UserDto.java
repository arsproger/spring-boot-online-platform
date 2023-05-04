package com.it.academy.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserDto {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
}
