package com.it.academy.controllers;

import com.it.academy.dto.AuthenticationDto;
import com.it.academy.dto.UserDto;
import com.it.academy.entities.User;
import com.it.academy.enums.UserStatus;
import com.it.academy.mappers.UserMapper;
import com.it.academy.security.DetailsUser;
import com.it.academy.security.JWTUtil;
import com.it.academy.services.UserService;
import com.it.academy.util.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
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
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody @Valid UserDto userDTO, BindingResult bindingResult) {
        userValidator.validate(userMapper.map(userDTO), bindingResult);

        if (bindingResult.hasErrors()) {
            Map<String, String> message = Map.of("message", Objects.requireNonNull(Objects.requireNonNull(
                    bindingResult.getFieldError()).getDefaultMessage()));
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

        userService.create(userMapper.map(userDTO));

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());

        authenticationManager.authenticate(authInputToken);

        String token = jwtUtil.generateToken(userDTO.getEmail());
        Map<String, String> response = Map.of("token", token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя",
            description = "Если при авторизации данные будут неверными, " +
                    "отправится ответ с ключом message и его сообщением")
    public ResponseEntity<Map<String, String>> performLogin(@RequestBody @Valid AuthenticationDto authenticationDTO, BindingResult bindingResult) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());

        if (bindingResult.hasErrors()) {
            Map<String, String> message = Map.of("message", Objects.requireNonNull(Objects.requireNonNull(
                    bindingResult.getFieldError()).getDefaultMessage()));
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            Map<String, String> message = Map.of("message", "Неверный логин или пароль!");
            return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
        }

        Optional<User> user = userService.getByEmail(authenticationDTO.getUsername());
        if (user.isEmpty() || user.get().getStatus() != UserStatus.ACTIVE) {
            return new ResponseEntity<>(Map.of("message", "User is not active!"), HttpStatus.FORBIDDEN);
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        Map<String, String> response = Map.of("token", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/oauth2/redirect")
    public ResponseEntity<Map<String, String>> redirect(@AuthenticationPrincipal DetailsUser detailsUser) {
        String token = jwtUtil.generateToken(detailsUser.getUsername());
        Map<String, String> response = Map.of("token", token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

