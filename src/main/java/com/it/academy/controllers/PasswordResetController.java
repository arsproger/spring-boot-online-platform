package com.it.academy.controllers;

import com.it.academy.services.PasswordResetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordResetController {
    private final PasswordResetService passwordResetService;

    @PostMapping("/reset")
    @Operation(summary = "Запрос на восстановление пароля")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestParam String email) {
        return passwordResetService.resetPassword(email);
    }

    @PostMapping("/reset/{resetToken}")
    @Operation(summary = "Проверка и установление нового пароля")
    public ResponseEntity<Map<String, String>> saveNewPassword(@PathVariable String resetToken, @RequestParam String newPassword) {
        return passwordResetService.saveNewPassword(resetToken, newPassword);
    }

}


