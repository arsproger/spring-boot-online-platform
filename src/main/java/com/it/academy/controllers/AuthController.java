package com.it.academy.controllers;

import com.it.academy.dtos.AuthenticationDTO;
import com.it.academy.dtos.UserDTO;
import com.it.academy.enums.UserStatus;
import com.it.academy.mappers.UserMapper;
import com.it.academy.models.User;
import com.it.academy.security.JWTUtil;
import com.it.academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, UserMapper userMapper,
                          AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @GetMapping("/activate")
    public String activateAccount(@RequestParam("email") String email, @RequestParam("token") String token) {
        return userService.activateAccount(email, token);
    }

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody @Valid UserDTO userDTO) {
        userService.save(userMapper.convertToEntity(userDTO));

        String token = jwtUtil.generateToken(userDTO.getEmail());
        return Map.of("token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        Optional<User> developer = userService.getByEmail(authenticationDTO.getUsername());

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        if (developer.isEmpty() || developer.get().getStatus() != UserStatus.ACTIVE)
            return Map.of("message", "User is not active!");

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("token", token);
    }

}

