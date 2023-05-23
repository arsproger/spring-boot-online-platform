package com.it.academy.controllers;

import com.it.academy.dto.AuthenticationDto;
import com.it.academy.dto.UserDto;
import com.it.academy.mappers.UserMapper;
import com.it.academy.security.JWTUtil;
import com.it.academy.services.UserService;
import com.it.academy.util.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Контроллер для регистрации и авторизации")
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserValidator userValidator;

    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя",
            description = "Если при регистрации email будет уже занят, " +
                    "отработает валидация и отправит ответ с ключом message и его сообщением")
    public Map<String, String> registerUser(@RequestBody @Valid UserDto userDTO, BindingResult bindingResult) {
        userValidator.validate(userMapper.map(userDTO), bindingResult);

        if (bindingResult.hasErrors())
            return Map.of("message", Objects.requireNonNull(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()));

        userService.save(userMapper.map(userDTO));

        String token = jwtUtil.generateToken(userDTO.getEmail());
        return Map.of("token", token);
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя",
            description = "Если при авторизации данные будут неверными, " +
                    "отправится ответ с ключом message и его сообщением")
    public Map<String, String> performLogin(@RequestBody @Valid AuthenticationDto authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("token", token);
    }

}

